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

import java.util.StringTokenizer;
import java.util.ResourceBundle;
import java.util.Hashtable;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;

import javax.servlet.util.StringHash;
import javax.servlet.util.Dispatcher;
import javax.servlet.util.Unchecked;

import java.util.zip.GZIPOutputStream;

public class JGICore extends HttpServlet // implements SingleThreadModel
{
    public final static String COOKIE = "COOKIE";
    final static String DEBUG = "_debug";

    static ResourceBundle rb;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected PrintWriter out;

    protected Hashtable cookies;
    protected StringHash params;

    String [] dirs;
    String path;
    protected String last;

    protected String ext;

    static String [] default_roles = new String [] {"main"};
    public String [] roles() { return default_roles; }

    public void doPost(HttpServletRequest request,
			HttpServletResponse response) {
	doGet( request, response );
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
	this.request = request;
	this.response = response;

	params = new StringHash();
	ext = null;

	path = path_info();
	if ( (path = path_info()) == null )
	    path = "";

	int index;
	if ( (index = path.lastIndexOf( '.' )) > 0 ) {
	    ext = path.substring( index+1 );
	    path = path.substring( 0, index );

	    if ( (index = ext.indexOf( '/' )) > 0 ) {
		path += ext.substring( index );
		ext = path.substring( 0, index-1 );
	    }
	}

	dirs = split( "/", path );
	String [] roles = roles();

	for ( int i = 0 ; i <dirs.length ; i++ ) {
	    String dir = dirs[i];

	    if ( (index = dir.indexOf( "=" )) > 0 )
		params.put( dir.substring( 0, index ),
			    dir.substring( index+1 ) );
	    else if ( roles != null && i < roles.length )
		params.put( roles[i], dir );
	}
	
	/* this does not work - cloassloader problem ?? */
   	//params = new StringHash( request, "Parameter" );

	/* present header values as parameters */
	String [] names = StringHash.array( request.getHeaderNames() );
	for( int i=0 ; i<names.length ; i++ ) {
	    String name = names[i];
	    Object value = request.getHeader( name );

	    if ( !(name.length() > 7 &&
		   name.substring(0, 8).equalsIgnoreCase( "CONTENT_" )) )
		name = "HTTP_"+name;
	    params.put( name, value );
	}

	Cookie [] cookieArray = request.getCookies();
	cookies = new Hashtable();

	for( int i=0 ; i<cookieArray.length ; i++ )
	    gotCookie( cookieArray[i] );
	endCookies();

	/* bring in GET/POST parameters */
	names = StringHash.array( request.getParameterNames() );
	for( int i=0 ; i<names.length ; i++ ) {
//	    System.out.println( names[i]+" = "+request.getParameter(names[i]));
	    params.put( names[i], request.getParameter(  names[i] ) );
	}

	last = path.length() == 0 ? getClass().getName()+"/" : 
	    path.charAt( path.length()-1 ) != '/' ?
	    dirs[dirs.length-1]+"/" : "";

	doPage();

	if ( out != null )
	    out.close();

	request = null;
	response = null;

	// log( "fin "+url() );
    }

    protected void gotCookie( Cookie cookie ) {
	String cookieName = cookie.getName();
	//System.err.println( "Got cookie: "+cookieName );

	cookies.put( cookieName, cookie );
	params.put( COOKIE+cookieName, cookie.getValue() );
    }

    protected void endCookies() {
    }

    protected void setCookie( Cookie cookie ) {
	response.addCookie( cookie );
    }

    protected void doPage() {

	if ( ext == null ) {
	    String [] roles = roles();

	    if ( dirs.length < roles.length  )
		ext = roles[dirs.length].substring( 1 );
	    else
		ext = default_roles[0];
	}

	//log( this+" doPage "+path+" + "+ext );

	if ( !isFrame() ) {
	    Dispatcher d = new Dispatcher( this, ext+"_page" );
	    if ( d.method == null )
		d = new Dispatcher( this, "default_page" );
	    d.invoke( this );
	}

	if ( params.get( DEBUG ) != null ) {
	    out.println( "<br><pre>" );
	    String [] keys = params.sortedKeys();

	    for ( int i=0 ; i<keys.length ; i++ ) {
		out.println( keys[i]+": "+params.get( keys[i] ) );
	    }
	}
    }

    public void default_page() {
	print( page_title( "not implemented" )+
	       "<h3>Page type \""+ext+"\" not implemented</h3>" );
    }

    protected void print (String str1) {
	out.print( str1 );
    }

    /* these are abstract */
    public Hashtable frames () { return null; }

    public String frame_title() { return "JGI Frame - "+ext; }

    public boolean isFrame () {
	Hashtable frames = frames();

	if ( frames != null ) {
	    Object [] fset = (Object [])(frames.get( ext ));

	    if ( fset != null ) {
		ext = "frame";
		String fm = header()+tag( "title", null, frame_title() )+
		    "<frameset name="+ext+" "+(String)fset[0]+">\n";

		for ( int i=1 ; i<fset.length ; i++ ) {
		    String [] f = (String [])fset[i];
		
		    fm += "    <frame name="+f[0]+
			" src='"+script_name()+path+"."+f[0];
		    if ( query_string() != null )
			fm += "?"+query_string();
		    fm += "' "+f[1]+">\n";
		}

		out.print( fm+"</frameset>\n" );
		return true;
	    }
	}

	return false;
    }

    public void warn (String str) { log( str ); }

    /*
    public String getLocalString( String prop ) {
	if ( rb == null )
	    rb = ResourceBundle.getBundle("LocalStrings", request.getLocale());

	try { 
	    return rb.getString( prop );
	}
	catch (MissingResourceException e) {
	    return "missing resource: "+prop;
	}
    }
    */

    String map (String template, String [] arr, String join) {
	int index = template.indexOf( "$_" );
	String pre = template.substring( 0, index );
	String post = template.substring( index+2 );

	StringBuffer b = new StringBuffer();
	for ( int i=0 ; i<arr.length ; i++ )
	    b.append( pre+arr[i]+post+join );

	return b.toString();
    }

    protected StringHash param() {
	return params;
    }

    protected String param( String name ) {
	return params.get( name );
    }

    static public Hashtable expires = new Hashtable();

    public void setExpires( double minutes ) {
	response.setDateHeader( "Expires", 
				new java.util.Date().getTime()+
				(long)(minutes*60*1000) );
    }

    protected String header ( String type ) {

	String minutes = (String)expires.get( ext!=null ? ext : "html" );
	if ( minutes != null )
	    setExpires( Double.parseDouble( minutes ) );

        response.setContentType(type);
	String encoding = request.getHeader( "Accept-Encoding" );

	try {
	    if ( false &&
		 encoding != null && encoding.indexOf( "gzip" ) >= 0 ) {
		OutputStream out1 = response.getOutputStream();
		out = new PrintWriter(new GZIPOutputStream(out1), false);
		response.setHeader( "Content-Encoding", "gzip" );
	    }
	    else
		throw new IOException( "browser cannot unzip" );
	}
	catch (IOException e) {
	    //	    log( "Could not gzip output as "+e.getMessage() );
	    try {
		out = response.getWriter();
	    }
	    catch (IOException w) {
		throw new Unchecked( w, "Could not get writer" );
	    }
	}

	return "";
    }

    protected String header () {
	return header( response.CHARSET == null ? "text/html" :
		       "text/html; charset="+response.CHARSET.toLowerCase() );
    }

    protected String header (String[] attr) {
	return header();
    }

    protected String stylesheet () {
	return null;
    }

    protected String ssheet( String path ) {
	return tag( "link", new String [] {"rel", "stylesheet", "href", path,
					   "type", "text/css"}, null );
    }

    protected String salt() {
	return "";
    }

    protected String start_html( String title ) {
	String stylesheet = stylesheet();
	int idx = stylesheet.indexOf( '?' );
	String filesheet = idx < 0 ?
	    stylesheet : stylesheet.substring( 0, idx );

	return tag( "html", null, null )+
	    tag( "head", null, tag( "title", null, title )+
		 (stylesheet == null ? "" : ssheet( stylesheet ) )+
		 ssheet( "file://c:"+filesheet )+
		 ssheet( "file://"+org.jhttpd.Util.home()+filesheet ) )+
	    salt()+tag( "body", new String [] {"class", ext}, null );
    }

    protected String page_title( String title ) {
	return header()+start_html( title );
    }

    protected String script_name()  {
	return request.getContextPath()+request.getServletPath();
    }

    protected String path_info()    { 
	String path_info = request.getPathInfo();
	return path_info != null ? unescape( path_info ) : "";
    }

    protected String query_string() { return request.getQueryString(); }
    protected String self_url()     { return "http://"+host()+
					  script_name()+path_info(); }
    protected String host()         { return request.getHeader( "Host" ); }
    protected String url()          { return request.getRequestURI(); }

    public void flush() {
    	out.flush();
    }

    static public  String l(String from) {
	return l(from, from, null, null, null);
    }

    static public String l(String from, String to) {
	return l(from, to, null, null, null);
    }

    static public String l(String from, String to, String name) {
	return l(from, to, name, null, null);
    }

    static public String l( String from, String to, String name,
			    String target ) {
	return l(from, to, name, target, null);
    }

    static public String l( String from, String to, String name,
			    String target, String title ) {
	if ( to == null )
	    to = from;
	if ( name != null )
	    to += "' name='"+name;
	if ( target != null )
	    to += "' target='"+target;
	if ( title != null )
	    to += "' title='"+title;

	return "<a href='"+to+"'>"+from+"</a>"; //escapeHTML??
    }

    static public String th( String attr[], String cells[] ) {
	StringBuffer b = new StringBuffer();
	for ( int i=0 ; i<cells.length ; i++ )
	    b.append( tag( "th", attr, cells[i] ) );
	return b.toString();
    }

    static public String td( String attr[], String cells[] ) {
	StringBuffer b = new StringBuffer();
	for ( int i=0 ; i<cells.length ; i++ )
	    b.append( tag( "td", attr, cells[i] ) );
	return b.toString();
    }

    static public String tag( String tag, String[] attr, String str ) {
	String etag = tag;

	for( int i=0 ; attr!=null && i<attr.length ; i+=2 ) {
	    tag = tag + " " + attr[i]+(i+1<attr.length && attr[i+1] != null ?
				       "='"+escapeHTML(attr[i+1])+"'" : "" );
	}

	return "\n<"+tag+">"+(str!=null ? str+"</"+etag+">" : "");
    }

    public String inputs( String type, String [] attr, String name ) {

	if ( name != null ) {
	    type += " name='"+name+"'";
	}

	boolean hasValue = false;
	if ( attr != null )
	    for( int i=0 ; i<attr.length ; i+=2 )
		if ( attr[i] != null && attr[i+1] != null ) {
		    if ( "value".equals( attr[i] ) )
			hasValue = true;
		    type += " " + attr[i]+"='"+escapeHTML(attr[i+1])+"'";
		}

	if ( name != null && !hasValue ) {
	    String param = param( name );
	    if ( param != null )
		type += " value='"+escapeHTML(param)+"'";
	}

	return "<input type="+type+">";
    }

    public String inputi( String img, String title, String accessKey,
			  String attr[] ) {
	String nattr[] = new String [12+(attr!=null?attr.length:0)];
	nattr[0] = "src";         nattr[1] = img+".gif";
	nattr[2] = "onmousedown"; nattr[3] = "this.src = \""+img+"2.gif\";";
	nattr[4] = "onmouseout";  nattr[5] = "this.src = \""+img+".gif\";";
	nattr[6] = "onmouseup";   nattr[7] = "this.src = \""+img+".gif\";";
	nattr[8] = "title";       nattr[9] = title;
	nattr[10] = "accessKey"; nattr[11] = accessKey;
	
	for ( int i=0 ; attr!=null && i<attr.length ; i++ )
	    nattr[12+i] = attr[i];

	return inputs( "image", nattr, null );
    }

    public String popup_menu( String name, String [] opts, String dflt ) {
	StringBuffer sel = new StringBuffer( "<select name="+name+">\n" );

	if ( dflt == null )
	    dflt = param( name );

	if ( opts != null )
	    for( int i=0 ; i<opts.length ; i++ ) {
		if ( opts[i] == null )
		    continue;
		String opt = "<option value='"+escapeHTML( opts[i] )+"'";
		if ( dflt != null && opts[i].equals( dflt ) )
		    opt += " selected";
		sel.append( opt+">"+opts[i]+"\n" );
	    }

	sel.append( "</select>\n" );
	return sel.toString();
    }

    static public String check_box( String name, boolean checked, String label ) {
	return "<input name='"+name+"' type='checkbox' "+
	    (checked?"checked":"")+">"+label;
    }

    public static String nbsp( String in ) {
	return in.replaceAll( " ", "&nbsp;" );
    }

    static public String unescape( String str ) {
	StringBuffer buff = new StringBuffer( str );
	int index;
	while ( (index = buff.toString().indexOf( "%" )) >= 0 ) {
	    String hex = buff.toString().substring( index+1, index+3 );
	    Integer i = Integer.valueOf( hex, 16 );
	    Character c = new Character((char)i.intValue());
	    // buff.replace( index, index+3, c.toString() );
	    buff = new StringBuffer( buff.toString().substring( 0, index )+
				     c.toString()+
				     buff.toString().substring( index+3 ));
	}
	return buff.toString();
    }

    static public StringBuffer escape (String value) {
	StringBuffer str = new StringBuffer( value );
/*
	for( int i=0 ; i<str.length() ; i++ ) {
	    char ch = str.charAt( i );
	    if ( !Character.isLetterOrDigit( ch ) &&
		 ch != '.' && ch != '-' ) {
		str.replace( i, i+1, "%"+Integer.toHexString( ch ) );
log( ch+" "+Integer.toHexString( ch ) );
	    }
	}
*/
	return str;
    }

    public static String escapeHTML (String value) {
	if ( value == null ) return "";
	StringBuffer str = new StringBuffer();

	for( int i=0 ; i<value.length() ; i++ ) {
	    char ch = value.charAt( i );
	    switch ( ch ) {
	    case'\'': str.append( "&#39;" ); break;
	    case '"': str.append( "&quot;"); break;
	    case '&': str.append( "&amp;" ); break;
	    case '<': str.append( "&lt;"  ); break;
	    case '>': str.append( "&gt;"  ); break;
	    default: str.append( new char[] {ch} );
	    }
	}

	return str.toString();
    }

    static public String [] split( String pat, String string ) {
	StringTokenizer tokeniser = new StringTokenizer( string, pat );
	int tokenCount = tokeniser.countTokens();
	String [] tokens = new String [tokenCount];

	for ( int i=0 ; i<tokenCount ; i++ )
	    tokens[i] = tokeniser.nextToken();

	return tokens;
    }

}

