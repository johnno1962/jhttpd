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

import java.util.TimeZone;
import java.util.Calendar;
import java.util.Date;

import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.IOException;

public class HttpServletResponse extends HttpSession {

    static TimeZone tz = TimeZone.getTimeZone( "UTC" );
    static String nl = "\r\n", days[] = new String [] {
	"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

    static String gmtDate( long time ) {
	Calendar c = Calendar.getInstance( tz );
	Date d = new Date( time ); c.setTime( d );
	return days[ c.get( Calendar.DAY_OF_WEEK )-Calendar.SUNDAY ]+
	    ", "+d.toGMTString();
    }

    StringBuffer header = new StringBuffer();
    int status = 200;

    public void setStatus( int status ) {
	this.status = status;
    }

    public void setHeader( String name, String value ) {
	header.append( name+": "+value+nl );
    }

    public void setDateHeader( String name, long time ) {
	if ( header.indexOf( "Date: " ) < 0 )
	    setHeader( "Date", gmtDate( System.currentTimeMillis() ) );
	setHeader( name, gmtDate( time ) );
    }

    public void setContentType ( String type ) {
	setHeader( "Content-Type", type != null ? type : "text/plain" );
    }

    public void setContentLength ( int length ) {
	setHeader( "Content-Length", ""+length );
    }

    public void addCookie( Cookie cookie ) {
	if ( header.indexOf( "Date: " ) < 0 )
	    setHeader( "Date", gmtDate( System.currentTimeMillis() ) );
	cookie.add( this );
    }

    public OutputStream getOutputStream () {
	//	PrintWriter hout = new PrintWriter( new 
	//OutputStreamWriter( outs ) );
	try {
	    String resp = "HTTP/1.0 "+status+" OK"+nl+
		"Connection: close"+nl+header+nl; 
	    //System.out.print( ">>"+resp );
	    outs.write( resp.getBytes() );
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    //	hout.print( header+"\r\n" );
    //	hout.flush();

	return outs;
    }

    public PrintWriter getWriter() throws IOException {
	return new PrintWriter( CHARSET == null ?
				new OutputStreamWriter( getOutputStream() ) :
				new OutputStreamWriter( getOutputStream(), 
							CHARSET ) );
    }

    public PrintWriter rawWriter() {
	//return new PrintWriter( new OutputStreamWriter( outs ), true );
	return new PrintWriter( outs, true );
    }

    public void sendRedirect( String location ) {
	PrintWriter hout = rawWriter();
	hout.print( "HTTP/1.0 302 Moved"+nl );
	hout.print( "Status: 302 Moved"+nl );
	hout.print( "Location: "+location+nl );
	hout.print( header );
	//System.out.println( ">>"+header );
	hout.close();
    }
}
