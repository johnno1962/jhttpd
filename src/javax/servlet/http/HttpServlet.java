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

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import java.io.IOException;

public class HttpServlet {

    public void init () throws ServletException {
    }

    public void init (ServletContext c) throws ServletException {
	init();
    }

    public void service( HttpServletRequest request,
			 HttpServletResponse response )
	throws ServletException, IOException {

	    if ( request.getMethod().equals( "POST" ) )
		 doPost( request, response );
	    else
		 doGet(  request, response );
    }

    public void doPost( HttpServletRequest request,
			HttpServletResponse response )
	throws ServletException, IOException {
	throw new IOException( "POST not implemented" );
    }

    public void doGet( HttpServletRequest request,
		       HttpServletResponse response )
	throws ServletException, IOException {
	throw new IOException( "GET not implemented" );
    }

    public void cancel () {
	System.err.println( "cancel" );
    }

    public void destroy () {
    }

    static public void log (String msg) {
	System.err.println( msg );
    }

    public String supportAddress() {
	return "";
    }

    public String version() {
	return "";
    }

}
