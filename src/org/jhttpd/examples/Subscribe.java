
package org.jhttpd.examples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.Date;

public class Subscribe extends HttpServlet {

    PrintWriter out;
    String channel;
    String command;
    Date when;

    public void service( HttpServletRequest req,
			 HttpServletResponse resp )
	throws ServletException, IOException {

	resp.setContentType( "text/plain" );
	out = resp.getWriter();
	when = new Date();

	channel = req.getParameter( "channel" );
	command = req.getParameter( "command" );
	    
	Publisher.subscribe( this, channel, command );

	out.close();
    }

    public void cancel() {
	Publisher.unsubscribe( this, channel );
    }

}
