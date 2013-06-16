
package org.jhttpd.examples;

import javax.servlet.http.JGIServlet;
import javax.servlet.http.Cookie;

import java.util.Enumeration;

public class HelloWorld extends JGIServlet {

    static int count = 0;

    public String stylesheet() {
	return "/jsqldocs/jsql.css";
    }

    protected void doPage() {
	String value = param( "popup" );
	if ( value != null )
	    response.addCookie( new Cookie( "test", value ) );

	print( page_title( "Hello World Servlet" ),
	       style( "body { font: 10pt Arial; }" ),
	       h2( "style='color:red'", "Hello World, run #"+count++ ), p(),
	       b( "Servlet: " ), request.getRequestURL(), br(),
	       b( "PathInfo: " )+request.getPathInfo(), p() );

	print( b( attr( "style", "color: brown;" ), "Headers:"), br() );
	Enumeration i = request.getHeaderNames();
	while ( i.hasMoreElements() ) {
	    String key = (String)i.nextElement();
	    print( b( key+": " )+request.getHeader( key )+br() );
	}

	print( p(), b( "style='color: green;'", "Parameters: " ), br() );
	i = request.getParameterNames();
	while ( i.hasMoreElements() ) {
	    String key = (String)i.nextElement();
	    print( b( key+": " )+request.getParameter( key )+br() );
	}

	print( start_form( attr( "action", request.getRequestURL() ) ), 
	       b( "Text: " ), inputs( "text", attr( "size", "5" ), "input" ),
	       br(), check_box( "cb", param( "cb" ) != null, "cb" ), " ",
	       popup_menu( "popup", attr( "one", "two", "three" ), value ),
	       br(), inputs( "submit", attr( "value", "Test" ), null ),
	       script( "document.forms[0].input.select();" ) );
    }

}

