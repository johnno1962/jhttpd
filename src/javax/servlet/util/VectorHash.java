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

import java.util.Hashtable;
import java.util.Vector;

import java.lang.reflect.Array;

public class VectorHash extends Hashtable {

    public VectorHash () {};

    public Vector get( String key ) {
	return (Vector)super.get( key );
    }

    public Vector put( String key, Object value ) {
	Vector v = get( key );
	if ( v == null )
	    super.put( key, v = new Vector() );
	v.add( value );
	return v;
    }

    public String firstString( String key ) {
	Vector v = get( key );
	return v != null ? (String)v.get(0) : null;
    }

    public String lastString( String key ) {
	Vector v = get( key );
	return v != null ? (String)v.get(v.size()-1) : null;
    }

    public Object [] array( String key, Class c ) {
	Vector v = get( key );	
	return v != null ? v.toArray( (Object [])Array
				      .newInstance( c, v.size() ) ) : null;
    }

}
