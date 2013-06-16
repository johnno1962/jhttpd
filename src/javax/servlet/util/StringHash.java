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

import java.util.Enumeration;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Vector;

public class StringHash extends Hashtable {

    public StringHash () {};

    public StringHash ( Object enumerable, String selectorBase ) {
	super();

	Enumeration names = (Enumeration)
	    Dispatcher.dispatch( enumerable, "get"+selectorBase+"Names" );
	Dispatcher getter = new Dispatcher( enumerable, "get"+selectorBase,
					    new Class [] {String.class} );
	String parameterName;

	while ( names.hasMoreElements() &&
		(parameterName = (String)names.nextElement()) != null ) {
	    Object value = getter.invoke( enumerable,
					  new Object [] {parameterName} );
	    put( parameterName, value );
	}
    }

    public String get( String key ) {
	return (String)super.get( key );
    }

    public Vector getVector( String key ) {
	return (Vector)super.get( key );
    }

    public String get( String key, String dflt ) {
	String val = (String)super.get( key );
	if ( val == null )
	    val = dflt;
	return val;
    }

    public String [] sortedKeys () {
	return sortedKeys( this );
    }

    public static String [] sortedKeys( Hashtable h ) {
	return sort( array( h.keys() ) );
    }

    public static String [] array( Enumeration e ) {
	Vector v = new Vector();

	while ( e.hasMoreElements() )
	    v.addElement( e.nextElement() );

	return (String []) v.toArray( new String [v.size()] );
    }

    public static class Insentive implements Comparator {
	public int compare( Object o1, Object o2 ) {
	    return ((String)o1).toLowerCase()
		.compareTo( ((String)o2).toLowerCase() );
	}
    }

    public static String [] sort( String array[] ) {
	java.util.Arrays.sort( array, new Insentive() );
	return array;
    }

}
