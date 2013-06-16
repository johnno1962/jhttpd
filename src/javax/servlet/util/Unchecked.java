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

package javax.servlet.util;

public class Unchecked extends Error {

    public Unchecked ( Throwable e, String msg ) {
	super( msg+(e!=null?": "+e.getMessage():""), e );
    }

    static public void trace( String msg ) {
	try {
	    throw new Error( msg );
	}
	catch ( Error e ) {
	    e.printStackTrace();
	}
    }	

    /*
	this.e = e;
	this.msg = msg;
    }

    Throwable e;
    String msg;

    public void printStackTrace( PrintWriter p ) {
	if ( msg != null )
	    p.print( msg );
	e.printStackTrace( p );
    }

    public void printStackTrace( PrintStream p ) {
	if ( msg != null )
	    p.print( msg );
	e.printStackTrace( p );
    }

    public String getMessage() {
	return msg!=null ? msg+"/"+e.getMessage() : e.getMessage();
    }

    public String toString() {
	return e.toString();
    }
    */
}
