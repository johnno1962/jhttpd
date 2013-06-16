
package org.jhttpd.examples;
import javax.servlet.http.JGIServlet;

public class GoodbyeWorld extends JGIServlet {

    static int count = 0;

    public String stylesheet() {
	return "/jsqldocs/jsql.css";
    }

    protected void doPage() {
	print( h2( "Server exiting..." ) );
	print(); // flushes
	System.exit(0);
    }

}

