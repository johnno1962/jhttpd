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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import java.util.Enumeration;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class cgi_bin extends HttpServlet implements Runnable {

    Process proc;
    InputStream stderr;
    StringBuffer cmd = new StringBuffer();

    String escape( String in ) {
	return in != null ? in.replaceAll( "'", "'\\\\''" ) : "";
    }

    void export( String var, String value ) {
	cmd.append( "export "+var+"='"+escape(value)+"'; " );
    }

    public void service( HttpServletRequest req,
			 HttpServletResponse resp )
	throws ServletException, IOException {

	String script = req.getPathInfo(), info = "";
	int i = script.indexOf( '/', 1 );
	if ( i >=0 ) {
	    info = script.substring( i );
	    script = script.substring( 0, i );
	}
	script = req.getServletPath()+script;

	export( "SERVER_SOFTWARE", "jhttpd" );
	export( "GATEWAY_INTERFACE", "CGI/1.0" );
	export( "SERVER_PROTOCOL", "HTTP/1.0" );
	export( "SERVER_NAME", req.getServerName() );
	export( "SERVER_PORT", ""+req.getServerPort() );
	export( "SCRIPT_NAME", script );
	export( "PATH_INFO", info );
	export( "PATH_TRANSLATED", "docs"+info );
	export( "REMOTE_HOST", req.getRemoteHost() );
	export( "REMOTE_ADDR", req.getRemoteAddr() );
	export( "REMOTE_USER", req.getRemoteUser() );

	String client = req.getHeader( "X-Forwarded-For" );
	if ( client == null )
	    client = req.getRemoteAddr();
	export( "CLIENT_ADDR", client );

	String method = req.getMethod();
	String query = req.getQueryString();
	export( "REQUEST_METHOD", method );
	if ( method.equals( "GET" ) )
	    export( "QUERY_STRING", query );

	String type = req.getContentType();
	if ( req.getContentType() != null )
	    export( "CONTENT_TYPE", type );

	int length = req.getContentLength();
	if ( length != 0 )
	    export( "CONTENT_LENGTH", ""+length );

	Enumeration e = req.getHeaderNames();
	while ( e.hasMoreElements() ) {
	    String key = (String)e.nextElement();
	    export( "HTTP_"+key.toUpperCase().replace( '-', '_' ),
		    req.getHeader(key) );
	}

	script = "docs"+script;
	if ( !new File( script ).canRead() )
	    throw new IOException( "Invalid script: "+script );

	cmd.append( escape( script ) );
	//System.out.println( cmd );

	proc = Runtime.getRuntime().exec( new String[] {"bash", "-c",
							cmd.toString()} );

	OutputStream os =  proc.getOutputStream();
	if ( method.equals( "POST" ) ) {
	    os.write( query.getBytes() );
	}
	os.close();

	stderr = proc.getErrorStream();
	Thread thread = new Thread( this );
	thread.start();

	InputStream in = proc.getInputStream();
	PrintWriter hout = resp.rawWriter();
	hout.println( "HTTP/1.0 200 OK" );

	int bytesRead;
	byte buffer[] = new byte[4096];
	while ( (bytesRead = in.read( buffer )) != -1 ) {
	    hout.write( new String( buffer, 0, bytesRead ) );
	    hout.flush();
	}
	in.close();
	hout.close();

	try {
	    int status = proc.waitFor();
	    if ( status != 0 )
		System.err.println( "*** Non zero exit status "+status+
				   " returned from script: "+script+" "+info );
	    thread.join();
	}
	catch ( InterruptedException ex ) {
	    ex.printStackTrace();
	}
    }

    public void run() {
	BufferedReader in
	    = new BufferedReader( new InputStreamReader( stderr ) );
	String line;

	try {
	    while ( (line = in.readLine()) != null )
		System.err.println( "CGI STDERR: "+line );
	    stderr.close();
	}
	catch ( IOException e ) {
	    e.printStackTrace();
	}
    }

    public void cancel() {
	System.out.println( "Destroying "+proc );
	proc.destroy();
    }
	
}
