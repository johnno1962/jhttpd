
//
// jhttpd Applet Web server - http://www.dbexplorer.com
// Copyright (C) 2007 Dynamic Browser Applications Ltd.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
//

package javax.servlet;

import javax.servlet.http.HttpServlet;
import java.net.Socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ServletContext implements Runnable {

    public ClassLoader loader;
    public Thread thread;
    public Socket client;

    public static String CHARSET = "UTF-8";

    protected InputStream ins;
    protected BufferedReader in;
    protected OutputStream outs;

    protected HttpServlet servlet;
    protected Monitor monitor;

    static boolean debug = false;

    // for debugging applets
    static public void setDebug( boolean debug ) {
	ServletContext.debug = debug;
    }

    static public void log( Object o, String msg ) {

	if ( !debug )
	    return;

	String s = o.toString();
	int i = s.indexOf( '@' );
	s = s.substring( i+1 );
	String fileName = "unknown";
	int lineNumber = 0;

	try {
	    throw new Throwable( "" );
	}
	catch ( Throwable t ) {
	    StackTraceElement e = t.getStackTrace()[1];
	    fileName = e.getFileName();
	    lineNumber = e.getLineNumber();
	}

	System.out.println( s+"#"+fileName+":"+lineNumber+" "+msg );
    }

    // entry point for new connection from client
    public void setClient(Socket browserClient, ClassLoader classLoader) {
	client = browserClient;
	loader = classLoader;
	try {
	    ins = client.getInputStream();
	    in = new BufferedReader( new InputStreamReader( ins, CHARSET ) );

	    outs = client.getOutputStream();
	    thread = new Thread( this, "Service" );
	    thread.start();
	}
	catch (Throwable e) {
	    e.printStackTrace();
	}
    }

    public void run() {
	// @see http/HttpServletRequest.java
    }

    public void cancel() {
	if ( servlet != null )
	    servlet.cancel();
    }

    public void monitor( String uri ) {
      (monitor = new Monitor( uri )).start();
    }

    public void monitored() {
      monitor.stop();
    }

    // watches for close of connection (cancel)
    public class Monitor extends Thread {

	String uri;

	Monitor( String uri ) {
	    super( "Monitor "+uri );
	    this.uri = uri;
	}

	public void run () {
	    byte buffer[] = new byte[1024];
	    int timeout = 10, count = 0;
	    String msg = "timed";

	    // System.err.println("monitoring");

	    do {
		try {
		    Thread.sleep( 500 ); client.setSoTimeout( 1 );
		    //client.setSoTimeout( timeout*1000 );
		    if ( ins.read(buffer) == -1 )
			throw new IOException( "Connection reset" );
		    // System.err.println("monitored" );
		}
		catch (Exception e) {
		    msg = e.getMessage();
		    if ( msg.indexOf( "Read timed out" ) >= 0 ) {
			System.err.print( "." ); System.err.flush();
			if ( ++count % (20*60/timeout) == 0 )
			    System.err.print( "\nWaiting "+(count/2)+
					      " seconds for "+uri+"\n" );
		    }
		    else {
			System.err.println( e.getClass().getName()+
					    ": "+msg+", "+uri );
			if ( msg != null && (
			 msg.indexOf( "Socket read failed" ) >= 0 ||
			 msg.indexOf( "Connection reset" ) >= 0 ) ) {
			    System.out.println( "Request cancelled "+uri );
			    cancel(); thread.stop(); break; /// stop(); ///
			}
		    }
		}
	    } while( true && msg.indexOf( "timed" ) > -1 );
	}
    }
}

