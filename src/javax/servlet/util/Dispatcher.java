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

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class Dispatcher {

    public Method method;

    static public Object dispatch( Object object, String selector ) {
	return (new Dispatcher( object, selector )).invoke( object );
    }

    public Dispatcher ( Object object, String selector ) {
	this( object, selector, new Class [] {} );
    }

    public Dispatcher ( Object object, String selector, Class [] args ) {
	this( object.getClass(), selector, args );
    }
	
    public Dispatcher ( Class objClass, String selector, Class [] args ) {

	try {
	    method = objClass.getMethod( selector, args );
	}
	catch (Exception e) {
	    System.err.println( "Could not find selector: "+selector+
				" in class: "+objClass.getName() );
	}
    }

    public Object invoke ( Object object ) {
	return invoke( method, object, new Object [] {} );
    }

    public Object invoke ( Object object, Object arg ) {
	return invoke( method, object, new Object [] {arg} );
    }

    public Object invoke ( Object object, Object [] args ) {
	return invoke( method, object, args );
    }

    static public Object invoke ( Method method,
				  Object object, Object [] args ) {
	try {
	    return method.invoke( object, args );
	}
	catch (InvocationTargetException i) {
	    Throwable e = i.getTargetException();
	    //	    e.printStackTrace();
	    throw new Unchecked( e, "Error when invoking "+method+" on "+
				 object.getClass().getName()+":\n"+
				 e.getClass().getName()+" "+e.getMessage() );
	}
	catch (Exception e) {
	    throw new Unchecked( e, "Error invoking "+method+" on "+
				 object.getClass().getName()+":\n"+
				 e.getClass().getName()+" "+e.getMessage() );
	}
    }

}
