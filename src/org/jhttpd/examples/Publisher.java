
package org.jhttpd.examples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import java.io.InputStream;
import java.io.IOException;


public class Publisher extends HttpServlet {

    static Hashtable channels = new Hashtable();
    static {
	(new Thread() {
		public void run() {
		    while ( true ) {
			try {
			    Thread.sleep( 60*1000 );
			    Enumeration keys = channels.keys();
			    while ( keys.hasMoreElements() ) {
				String key = (String)keys.nextElement();
				Vector subs = (Vector)channels.get(key);
				if ( subs.size() != 0 ) {
				    System.out.println( key+" keep alive "+
							subs.size() );
				    for ( int i=0 ; i<subs.size() ; i++ ) {
					Subscribe s = (Subscribe)subs.get(i);
					try {
					    s.out.println( "\n" );
					    s.out.flush();
					}
					catch ( Exception e ) {
					    e.printStackTrace();
					    
					}
				    }
				}
			    }
			}
			catch (Throwable e) {
			    e.printStackTrace();
			}
		    }
		}
	    }).start();
    }

    public void service( HttpServletRequest req,
			 HttpServletResponse resp )
	throws ServletException, IOException {

	String channel = req.getParameter( "channel" );
	String data = req.getQueryString();

	Vector subs = (Vector)channels.get( channel );
	Subscribe sub;

	while ( subs != null && subs.size() > 0 &&
		(sub = (Subscribe)subs.remove( 0 )) != null ) {
	    log( "notifying "+sub );
	    sub.out.print( data );
	    synchronized ( sub ) {
		sub.notify();
	    }
	}

	resp.setContentType( "text/html" );
	resp.getWriter().println( "done\n\ndone" );
    }

    static void subscribe( Subscribe sub, String channel, String command ) {
	Vector subs = (Vector)channels.get( channel );
	if ( subs == null )
	    channels.put( channel, subs = new Vector() );
	subs.add( sub );
	try {
		if ( command != null ) {
			Process proc = Runtime.getRuntime().exec( command );
			InputStream in = proc.getInputStream();

			int bytesRead;
			byte buffer[] = new byte[4096];
			while ( (bytesRead = in.read( buffer )) != -1 )
				System.out.write( buffer, 0, bytesRead );

			proc.waitFor();
			in.close();
		}

	    synchronized ( sub ) {
		log( sub+" waiting on "+channel );
		sub.wait();
	    }
	}
	catch ( Exception e ) {
	    e.printStackTrace();
	}
    }

    static void unsubscribe( Subscribe sub, String channel ) {
	((Vector)channels.get( channel )).remove( sub );
    }

}
