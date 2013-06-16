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

import java.net.URLEncoder;
import java.util.Date;

public class Cookie {

    String name, value, path, domain;
    long seconds;

    public Cookie( String name, String value ) {
	this.name = name;
	this.value = value;
    }

    public String getName() {
	return name;
    }

    public String getValue() {
	return value;
    }

    public void setValue( String value ) {
	this.value = value;
    }

    public void setPath( String path ) {
	this.path = path;
    }

    public void setDomain( String domain ) {
	this.domain = domain;
    }

    public void setMaxAge( long seconds ) {
	this.seconds = seconds;
    }

    public long getMaxAge() {
	return seconds;
    }

    public String toString() {
	StringBuffer b = new StringBuffer();
	b.append( URLEncoder.encode(name)+"="+URLEncoder.encode(value) );

	String expires = null;
	if ( seconds != 0 ) {
	    char [] cexp =
		HttpServletResponse.gmtDate( (new Date()).getTime()+
					     seconds*1000 ).toCharArray();
	    if ( cexp[7] == ' ' )
		cexp[7] = cexp[11] = '-';
	    else
		cexp[6] = cexp[10] = '-';

	    expires = new String( cexp );
	}

	if ( expires != null )
	    b.append( "; Expires="+expires );
	if ( path != null )
	    b.append( "; Path="+path );
	if ( domain != null )
	    b.append( "; Domain="+domain );

	return b.toString();
    }

    public void add( HttpServletResponse resp ) {
	resp.setHeader( "Set-Cookie", toString() );
    }

}
