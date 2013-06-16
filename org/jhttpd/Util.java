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


package org.jhttpd;

import java.util.Properties;
import java.util.Hashtable;
import java.util.Vector;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.File;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

//
// Utility functions for use in "signed" applet applications.
// Write access is only to files in the users home directory.
//

public final class Util implements Runnable {

    static Vector queue = new Vector();
    static Util util;
    Thread thread;
    int count;

    // not a good idea over the internet...
    private static boolean extendPriviledgesToUnsignedClasses = false;
 
    Util( boolean insecure ) {
	extendPriviledgesToUnsignedClasses = insecure;
	if ( util == null )
	    (thread = new Thread( util = this, "Signed" )).start();
    }

    public void run() {
	while ( true ) {
	    try {
		while ( queue.size() != 0 ) {
		    Task t = (Task)queue.remove(0);
		    //System.out.println( t.sel+" "+t.args );
		    new Thread( t, "Util "+t ).start();
		    thread.setName( "Signed["+(++count)+"]" );
		}
		synchronized (queue) {
		    queue.wait();
		}
	    }
	    catch ( Throwable t ) {
		t.printStackTrace();
	    }
	}
    }

    private static class Task implements Runnable {

	String sel; Object args[]; Object ret;

	Task( String sel, Object args[] ) {
	    this.sel = sel;
	    this.args = args;
	}

	public void run() {
	    Class sig[] = new Class[args.length];
	    for ( int i=0 ; i<args.length ; i++ )
		sig[i] = args[i].getClass();

	    try {
		if ( "setConfigurationTask".equals( sel ) )
		    ret = util.setConfigurationTask( (Configuration)args[0] );
		else
		    ret = Util.class.getMethod( sel, sig ).invoke( util, args );
	    }
	    catch ( java.lang.reflect.InvocationTargetException e ) {
		//e.printStackTrace();
		ret = e.getTargetException();
	    }
	    catch ( Exception e ) {
		//e.printStackTrace();
		ret = e;
	    }

	    //System.out.println( "waiting for task2" );
	    synchronized (this) {
		//System.out.println( "waiting for task3" );
		this.notify();
		//System.out.println( "waiting for task4" );

	    }
	}
    }

    private static Object perform( String sel, Object args[] ) {
	Task task = new Task( sel, args );

	try {
	    if ( util == null ) {
		new Util( false );
		Thread.sleep( 100 );
	    }

	    if ( extendPriviledgesToUnsignedClasses ) {
		synchronized ( task ) {
		    synchronized ( queue ) {
			queue.add( task );
			Thread.sleep( 10 );
			queue.notify();
		    }
		    //System.out.println( "waiting for task1" );
		    task.wait();
		    //System.out.println( "waiting for task5" );
		}
	    }
	    else
		task.run();

	}
	catch ( InterruptedException e ) {
	    e.printStackTrace();
	}

	if ( task.ret instanceof Throwable ) {
	    Throwable t = (Throwable)task.ret;
	    try {
		throw new Error( "Exception from org.jhttpd.Util", t );
	    }
	    catch ( Error e ) {
		//e.printStackTrace();
		throw new UtilError( "Util exception: "+t.getMessage(), t );
	    }
	}

	// System.out.println( "Util returns: "+task.ret );
	return task.ret;
    }

    private static class UtilError extends Error {
	UtilError( String msg, Throwable t ) {
	    super( msg, t );
	}
    }

    private static Object perform( String sel, String arg ) {
	return perform( sel, new String[] {arg} );
    }

    public static String getProperty( String key ) {
	return (String)perform( "getPropertyTask", key );
    }

    public String getPropertyTask( String key ) {
	return System.getProperty( key );
    }

    public static void setProperty( String key, String value ) {
	perform( "setPropertyTask", new String[] {key, value} );
	return;
    }

    public String setPropertyTask( String key, String value ) {
	System.setProperty( key, value );
	return null;
    }

    public static String getenv( String key ) {
	return (String)perform( "getenvTask", key );
    }

    public String getenvTask( String key ) {
	return System.getenv( key );
    }

    public static Object loadDriver( String driverName ) {
	return perform( "loadDriverTask", driverName );
    }

    public Object loadDriverTask( String driverName ) {
	try {
	    ClassLoader cl = getClass().getClassLoader();
	    System.out.println( "loadDriver: "+cl+" - "+driverName );
	    return cl.loadClass( driverName ).newInstance();
	}
	catch (ClassNotFoundException e) {
	    e.printStackTrace();
	    throw new Error( "Can't load driver class: "+driverName, e );
	}
	catch (Exception e) {
	    e.printStackTrace();
	    throw new Error( "Can't load driver class: "+driverName, e );
	}
    }

    static String _home;

    public static String home() {
	return (String)perform( "homeTask", "" );
    }

    public String homeTask( String key ) {
	try {
	    if ( _home == null )
		_home = System.getProperty( "user.home" );
	}
	catch ( java.security.AccessControlException e ) {
	    _home = "c:\\";
	}
	return _home;
    }

    private String absolutePath( String path ) {
	boolean isAbsolute = path.charAt(0) == '/' || path.charAt(1) == ':';
	return isAbsolute ? path : home()+"/"+path;
    }

    public static String [] list( String path ) {
	return (String[])perform( "listTask", path );
    }

    public String[] listTask( String path ) {
	return new File( absolutePath( path ) ).list();
    }

    public static boolean mkdir( String path ) {
	return ((Boolean)perform( "mkdirTask", path )).booleanValue();
    }

    public Boolean mkdirTask( String path ) {
	return new Boolean( new File( home()+"/"+path ).mkdir() );
    }

    public static BufferedReader fileReader( String path )
	throws IOException {
	return (BufferedReader)perform( "fileReaderTask", path );
    }

    public BufferedReader fileReaderTask( String path )
	throws IOException {
	return  new BufferedReader( new FileReader( absolutePath( path ) ) );
    }

    public static FileInputStream inputStream( String path )
	throws IOException {
	return (FileInputStream)perform( "inputStreamTask", path );
    }

    public FileInputStream inputStreamTask( String path )
	throws IOException {
	return new FileInputStream( absolutePath( path ) );
    }

    public static FileOutputStream outputStream( String path )
	throws IOException {
	return (FileOutputStream)perform( "outputStreamTask", path );
    }

    public FileOutputStream outputStreamTask( String path )
	throws IOException {
	if ( path.indexOf( ".." ) >= 0 )
	    throw new IOException( "Invalid path" );
	return new FileOutputStream( home()+"/"+path );
    }

    public static Class loadClass( ClassLoader cl, String name )
        throws IOException {
	return (Class)perform( "loadClassTask", new Object[] {cl, name} );
    }

    public Class loadClassTask( ClassLoader cl, String name )
        throws IOException {
	try {
	    return cl.loadClass( name );
	}
	catch (Exception e) {
	    throw new IOException( e.getMessage() );
	}
    }

    public static Object newInstance( Class cl )
        throws IOException {
	return (Object)perform( "newInstanceTask", new Object[] {cl} );
    }

    public Object newInstanceTask( Class cl )
        throws IOException {
	try {
	    return cl.newInstance();
	}
	catch (Exception e) {
	    throw new IOException( e.getMessage() );
	}
    }

    public static Connection connect( String url, String user, String pass )
	throws SQLException {
	try {
	    return (Connection)perform( "connectTask", 
					new String[] {url, user, pass} );
	}
	catch (UtilError e) {
	    throw new SQLException( e.getMessage() );
	}
	catch (Error e) {
	    e.printStackTrace();
	    throw (SQLException)e.getCause();
	}
    }

    public Connection connectTask( String url, String user, String pass )
	throws SQLException {
	return DriverManager.getConnection( url, user, pass );
    }

    public static Connection connect( String url, Properties props )
	throws SQLException {
	try {
	    return (Connection)perform( "connectTask", 
					new Object[] {url, props} );
	}
	catch (UtilError e) {
	    throw new SQLException( e.getMessage() );
	}
	catch (Error e) {
	    e.printStackTrace();
	    throw (SQLException)e.getCause();
	}
    }

    public Connection connectTask( String url, Properties props )
	throws SQLException {
	return DriverManager.getConnection( url, props );
    }

    // keberos
    public static void setConfiguration( Configuration config ) {
	perform( "setConfigurationTask", new Object[] {config} );
	return;
    }

    public String setConfigurationTask( Configuration config ) {
	Configuration.setConfiguration( config );
	return null;
    }

    // tomcat
    public static Field [] getDeclaredFields( Class aClass ) {
	return (Field [])perform( "getDeclaredFieldsTask", new Object[] {aClass} );
    }

    public Field [] getDeclaredFieldsTask( Class aClass ) {
	return aClass.getDeclaredFields();
    }

    public static Method [] getDeclaredMethods( Class aClass ) {
	return (Method [])perform( "getDeclaredMethodsTask", new Object[] {aClass} );
    }

    public Method [] getDeclaredMethodsTask( Class aClass ) {
	return aClass.getDeclaredMethods();
    }

}

