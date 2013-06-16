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

package javax.servlet.http;

import javax.servlet.util.VectorHash;
import javax.servlet.ServletContext;

//import java.text.SimpleDateFormat;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.net.URLDecoder;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.Date;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.io.File;

import java.net.URL;

public class HttpServletRequest extends HttpServletResponse {

    static Hashtable isServlet = new Hashtable();
    static Hashtable types = new Hashtable() { {
	put( "", "text/html" );
	put( "ico", "image/x-icon" );
	put( "jpeg","image/jpeg" );
	put( "jpe", "image/jpeg" );
	put( "jpg", "image/jpeg" );
	put( "tiff","image/tiff" );
	put( "tif", "image/tiff" );
	put( "gif", "image/gif" );
	put( "png", "image/png" );
	put( "bmp", "image/bmp" );
	put( "css", "text/css" );
	put( "htm", "text/html" );
	put( "html","text/html" );
	put( "java","text/plain" );
	put( "psp", "text/plain" );
	put( "doc", "application/msword" );
	put( "xls", "application/vnd.ms-excel" );
	put( "ppt", "application/vnd.ms-powerpoint" );
	put( "pps", "application/vnd.ms-powerpoint" );
	put( "js",  "application/x-javascript" );
	put( "jse", "application/x-javascript" );
	put( "reg", "application/octet-stream" );
	put( "eps", "application/postscript" );
	put( "ps",  "application/postscript" );
	put( "gz",  "application/x-gzip" );
	put( "hta", "application/hta" );
	put( "jar", "application/zip" );
	put( "zip", "application/zip" );
	put( "pdf", "application/pdf" );
	put( "qt",  "video/quicktime" );
	put( "mov", "video/quicktime" );
	put( "avi", "video/x-msvideo" );
	put( "wav", "audio/x-wav" );
	put( "snd", "audio/basic" );
	put( "mid", "audio/basic" );
	put( "au",  "audio/basic" );
	put( "mpeg","video/mpeg" );
	put( "mpe", "video/mpeg" );
	put( "mpg", "video/mpeg" );
	put( "au",  "video/mpeg" );
    } };

    String method, uri, protocol;
    String host, servletPath, pathInfo, queryString;
    Hashtable headers;
    VectorHash parameters;
    Vector cookies = new Vector();

    private static Class platform;
    private static Method resolve;

    /*
    // http date format
    protected static SimpleDateFormat df = new
	SimpleDateFormat( "EEE, dd MMM yyyy HH:mm:ss" );
    static {
	df.setTimeZone( java.util.TimeZone.getTimeZone( "UTC" ) );
    }
    */

    public void run () {

	try {	    
	    String line = in.readLine();
	    StringTokenizer toks = new StringTokenizer( line, " " );

	    method   = toks.nextToken();
	    uri      = toks.nextToken();
	    protocol = toks.nextToken();

	    log( this, method+" "+uri );
	    thread.setName( "Service "+uri );

	    // parse request
	    if ( uri.indexOf( "http://" ) == 0 ) {
		int hend = uri.indexOf( '/', 7 );
		String host = uri.substring( 7, hend );
		String path = uri.substring( hend );
		System.err.println( "Proxy request: "+host+": "+path );
	    }

	    // separate query string
	    int index;
	    if ( (index = uri.indexOf( '?' )) >= 0 ) {
		pathInfo = uri.substring( 0, index );
		queryString = uri.substring( index+1 );
	    }
	    else
		pathInfo = uri;

	    pathInfo = URLDecoder.decode( pathInfo );
	    if ( pathInfo.indexOf( ".." ) >= 0 )
		throw new Error( "Invalid file path" );

	    // parse headers from client
	    headers = new Hashtable();
	    while ((line = in.readLine()) != null ) {

		if ( line.length() == 0 )
		    break;
		index = line.indexOf( ':' );
		String name = line.substring( 0, index );
		String value = line.substring( index+2 );

		// System.out.println( "PARAM> "+name+": "+value );

		// parse header containing cookies
		if ( name.equals( "Cookie" ) ) {
		    toks = new StringTokenizer( value, "; " );
		    while (toks.hasMoreTokens()) {
			String p = toks.nextToken();
			if ( (index = p.indexOf( '=' )) > 0 ) {
			    String n = p.substring( 0, index );
			    String v = decode( p.substring( index+1 ) );
			    cookies.addElement( new Cookie( n, v ) );
			    // System.err.println( "COOKIE: "+n+"="+v );
			}
		    }
		}
		else if ( name.equals( "Host" ) )
		    host = value;

		headers.put( name, value );
	    }

	    //log( this, "headers "+headers );
	    //log( this, "cookies "+cookies );

	    parameters = new VectorHash();
	    if ( queryString != null )
		parseParameters( queryString );

	    // read POST input
	    if ( method.equals( "POST" ) ) {
		int len = getContentLength(), read = 0;
		char buff[] = new char[len];
		while ( len > 0 && 
			(read = in.read( buff, buff.length-len, len )) != -1 )
		    len -= read;

		String type = getContentType();//, agent = getHeader("User-Agent");
		Matcher mtest = type != null ? pmt.matcher( type ) : null;
		boolean isMultipart = type != null ? mtest.matches() : false;
		//System.out.println( ">>>"+agent );

		/*
		if ( 1==0 && !isMultipart &&
		     agent.indexOf("Firefox/") == -1 &&
		     agent.indexOf("AppleWebKit/") == -1 )
		    in.read( new char [2], 0, 2 ); /// !!! ug
		*/

		queryString = new String( buff );
		//System.out.println( queryString );

		if ( isMultipart )
		    parseMultipart( queryString.split( "\r?\n--"+mtest.group(1) ) );
		else if ( type == null || !type.equals( "text/plain" ) )
		    parseParameters( queryString );
	    }

	    log( this, "parameters "+parameters );

	    // parse file path
	    String fullpath = pathInfo;
	    index = pathInfo.substring( 1 ).indexOf( '/' );
	    if ( index < 0 )
		index = pathInfo.length()-1;
	    String sname = pathInfo.substring( 1, index+1 );

		ClassLoader l = org.jhttpd.WebServer.loader;
	    try {

		log( this, "fetching "+uri );

		// already know this is a servlet?
		if ( isServlet.get( sname ) != null ||
		     "cgi-bin".equals( sname ) || "jsql".equals( sname ) )
		    throw new FileNotFoundException( "hack" );

		//log( this, "isfile? "+pathInfo );

		// server documents in "docs" directory
		File file = new File( "docs"+pathInfo );
		//log( this, "isfile? "+file );
		if ( file.isDirectory() ||
		     pathInfo.charAt( pathInfo.length()-1 ) == '/' )
		    file = new File( "docs"+pathInfo+"/index.html" );
		//log( this, "isfile? "+file );

		// check for doc in jar
		InputStream doc = null;
		log( this, "loader "+l );

		URL url =  /* this.getClass().getClassLoader() */ l
		    .getResource( "docs/"+pathInfo.substring( 1 ) );
		log( this, "URL "+url );

		// support for running inside eclipse
		if ( url != null && url.toString()
		     .indexOf( "bundleresource:" ) == 0 ) {
		    if ( platform == null ) {
			platform = Class
			    .forName( "org.eclipse.core.runtime.Platform" );
			resolve = platform
			    .getMethod( "resolve", new Class[] {URL.class} );
		    }
		    url = (URL)resolve.invoke( platform, new Object[] {url} );
		    //url = org.eclipse.core.runtime.Platform.resolve(url);
		    //System.out.println( "converted: "+url );
		}

		// fudge expiry to discourage re-fetch from jar docs
		if ( url != null && url.toString().indexOf( "jar:" ) == 0 ) {
		    log( this, "jar "+url.toString() );
		    setDateHeader( "Expires", new Date().getTime()+
				   (long)7*24*60*60*1000 );
		    doc = url.openStream();
		}
		log( this, "doc "+doc );
		// System.out.println( "URL: "+url );

		// try to read in document
		String stamp = null;
		if ( doc == null ) {
		    doc = new FileInputStream( file );
		    log( this, "file "+doc );
		    //Date mtime = new Date( file.lastModified() );
		    setDateHeader( "Last-Modified", file.lastModified() );
		    setHeader( "Content-Length", ""+file.length() );
		    stamp = gmtDate( file.lastModified() );
		    //stamp = df.format(new Date(file.lastModified()))+" GMT";
		}

		// determine document mime type
		index = pathInfo.lastIndexOf( '.' );
		String ext = index>0 ? pathInfo.substring( index+1 ) : "html";
		String mimeType = (String)types.get( ext );
		setContentType( mimeType );

		log( this, "type "+mimeType );

		// discourage re-fetch of images
		if ( mimeType != null && mimeType.indexOf( "image" )>=0 )
		     setDateHeader( "Expires", new Date().getTime()+
				    (long)30*24*60*60*1000 );

		// write docuemnt to client
		String since = getHeader( "If-Modified-Since" );
		if ( since != null ) {
		  if ( since.charAt( 5 ) == '0' )
		    since = since.substring( 0, 5 )+since.substring( 6 );
		  // System.out.println( pathInfo+" "+since+" "+stamp );
		}

		OutputStream os = null; /// NPE
		if ( false || stamp == null || since == null || !stamp
		     .equals( since.substring( 0, stamp.length() ) ) ) {
		    os = getOutputStream();
		    int bytesRead;
		    byte buffer[] = new byte[8*1024];
		    while ((bytesRead = doc.read( buffer ))!= -1) {
		        log( this, "write "+bytesRead );
		        os.write(buffer,0,bytesRead);
		    }
		}
		else {
		    setStatus( 304 );
		    os = getOutputStream();
		}

		os.flush();
		doc.close();
		client.close();
		//monitor.start();
		//monitor.stop();
		log( this, "file complete "+pathInfo );
	    }
	    catch (FileNotFoundException e) {

		///log( this, "isfile? "+pathInfo );
		// file not document 
		servletPath = "/"+sname;
		pathInfo = pathInfo.substring( index+1 );

		if ( servlet == null ) {

		    log( this, "loading class "+sname );

		    // try loading as servlet
		    Class cl = null;
		    try {
			//try {
			cl = l.loadClass( sname.replace( '-','_' ) );
			//cl = Class.forName( sname.replace( '-','_' ) );
			//}
			//catch ( ClassNotFoundException cnf ) {
			//System.err.println( "***** here" );
			// cl = Class.forName( sname.replace( '-','_' ),
			//true, loader );
		        //}

		        // file not found at all
		        if ( cl == null ) {
			    log( this, "file not found "+fullpath );
			    PrintWriter out = rawWriter();
			    out.println( "HTTP/1.0 404 Not Found" );
			    out.println( "Content-Type: text/plain\n" );
			    out.println( "File not found: "+fullpath );
			    out.close();
			    return;
		        }

			isServlet.put( sname, cl );
		    }
		    catch ( ClassFormatError fe ) {
			fe.printStackTrace();
		    }

		    // start servlet
		    servlet = (HttpServlet)cl.newInstance();
		    log( this, "servlet loaded "+servlet );
		    servlet.init( (ServletContext)this );
		}

		// monitor for cancel
		monitor( uri );
		log( this, "servlet start "+servlet );
		servlet.service( this, (HttpServletResponse)this );
		log( this, "servlet stop "+servlet );
		setDebug( false );
		monitored();

		// System.out.println( "Completed: "+method+" "+uri );
	    }
	}
	catch (java.net.SocketException e) {
	    System.out.println( "Broken pipe: "+uri );
	}
	catch (ThreadDeath t) {
	    System.err.println( "Thread stopped "+uri );
	    throw t;
	}
	catch (NoClassDefFoundError e) {
	    e.printStackTrace();
	    System.err.println(uri+": "+e);

	    PrintWriter hout = rawWriter();
	    hout.println( "\r\n\r\n<pre>Missing class on loading adaptor.\n"+
			  "Check your CLASSPATH or JSQL_CLASSPATH\n"+
			  "If using dbexplorer.com, select db type.\n" );
	    e.printStackTrace( hout );
	}
	catch (Throwable e) {
	    // any other error gets here
	    //	    hout.println( "Content-type: text/html\n" );
	    e.printStackTrace();
	    System.err.println(uri+": "+e);

	    PrintWriter hout = rawWriter();
	    if ( header.length() == 0 )
		hout.print( "HTTP/1.0 200 OK\r\n"+
			    "Content-type: text/html\r\n\r\n" );
	    hout.println( "<pre>\n" );
	    e.printStackTrace( hout );

	    ByteArrayOutputStream b = new ByteArrayOutputStream();
	    e.printStackTrace( new PrintStream( b ) );

	    String body = "Stack Trace Reported "+new Date()+"URL: "+uri+
		"\n\nPlease give us some idea what you where doing:\n\n\n\n"+
		b.toString()+(servlet!=null?"\nBuild: "+servlet.version():"")+
        "\nArch: "+System.getProperty("os.name");
	    String addr = servlet!=null ? servlet.supportAddress() : "a@b.com";
	    body = body.replaceAll( "(at )(?:com\\.dbexplorer\\.|"+
				   "javax\\.servlet\\.)(?:\\w+\\.)*"+
				    "([\\w<>]+\\()", "$1$2" );
	    hout.println( "</pre>To help us by reporting this error "+
			  "please click <a href='mailto:"+addr+
			  "?subject=Stack trace by servlet: "+servlet+
			  "&body="+URLEncoder.encode( body ).replace('+',' ')+
			  "'>here</a>." );
	    hout.close();
	}
	finally {
	    try { client.close(); }
	    catch(Exception e) {}
	}
    }

    static Pattern pmt = Pattern.compile("multipart/form-data; boundary=(.*)"),
	ppt = Pattern.compile( "name=\"([^\"]+)\"[^\n]*(?:\r?\n)+(.*)", Pattern.DOTALL );

    void parseMultipart( String parts[] ) {
	//System.out.println( ">>> "+parts.length );
	for ( int i=0 ; i<parts.length-1 ; i++ ) {
	    Matcher m = ppt.matcher( parts[i] );
	    if ( m.find() ) {
		String name = m.group(1), value = m.group(2);
		parameters.put( name, value );
		//System.out.println( name+" >"+value+"<" );
		/*
		if ( parameters.get( name ) == null )
		    parameters.put( name, new Vector() );
		value = value.trim();
		//System.out.println( name+" "+value );
		((Vector)parameters.get( name )).addElement( value );
		*/
	    }
	    else
		System.out.println( "No match on multipart" );
	}
    }

    void parseParameters( String input ) {
	StringTokenizer toks = new StringTokenizer( queryString, "&" );

	while (toks.hasMoreTokens()) {
	    String pair = toks.nextToken();
	    int index = pair.indexOf( '=' );

	    String name = index < 0 ? pair :
		pair.substring( 0, index );
	    String value = index < 0 ? "1" :
		decode( pair.substring( index+1 ) );

	    //System.out.println( ">>> "+name+"="+value );
	    if ( "_debug_jhttpd_".equals( name ) )
		setDebug( true );
	    else {
		parameters.put( name, value );
		/*
		Vector vals = (Vector)parameters.get( name );
		if ( vals == null )
		    parameters.put( name, vals = new Vector() );
		vals.addElement( value );
		*/
	    }
	}
    }

    public String getRemoteAddr() {
	return client.getInetAddress().getHostAddress();
    }

    public String getRemoteHost() {
	return client.getInetAddress().getHostName();
    }

    public HttpSession getSession() {
	return this;
    }

    public String getRemoteUser() {
	String user = getHeader( "X-Forwarded-For" );
	if ( user == null )
	    user = getRemoteAddr();
	return user;
    }

    public String getMethod() {
	return method;
    }

    public String getRequestURI() {
	return uri;
    }

    public String getProtocol() {
	return protocol;
    }

    public Enumeration getHeaderNames() {
	return headers.keys();
    }

    public String getHeader( String name ) {
	return (String)headers.get( name );
    }

    public String getContentType() {
	return getHeader( "Content-Type" );
    }

    public int getIntHeader( String name ) {
	String value = getHeader( name );
	return  value != null ? Integer.parseInt( value ) : 0;
    }

    public int getContentLength() {
	return getIntHeader( "Content-Length" );
    }

    public Enumeration getParameterNames() {
	return parameters.keys();
    }

    public String [] getParameterValues( String name ) {
	return (String [])parameters.array( name, String.class );
    }

    public String getParameter( String name ) {
	return parameters.firstString( name );
    }

    public Map getParameterMap() {
	return parameters;
    }

    public Cookie [] getCookies() {
	return (Cookie [])cookies.toArray( new Cookie[cookies.size()] );
    }

    public String getContextPath () {
	return "";//"http://"+host;
    }

    public String getServletPath () {
	return servletPath;
    }

    public String getRequestURL () {
	return "http://"+host+getServletPath();
    }

    public String getPathInfo() {
	return pathInfo;
    }

    public String getQueryString() {
	return queryString;
    }

    public static String decode(String s) {
	try {
	    return URLDecoder.decode( s, CHARSET == null ? "UTF-8" : CHARSET );
	}
	catch ( Exception e ) {
	    return s;
	}
    }

    public String getServerName() {
	int idx = host.indexOf( ':' );
	return idx == -1 ? host : host.substring( 0, idx );
    }
    
    public int getServerPort() {
	int idx = host.indexOf( ':' );
	return idx == -1 ? 80 : Integer.parseInt( host.substring( idx+1 ) );
    }

}
