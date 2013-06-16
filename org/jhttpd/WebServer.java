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

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;

import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;

import java.util.Enumeration;
import java.util.Hashtable;

import java.applet.Applet;
import com.sun.tools.javac.Main;

import java.lang.reflect.Method;
import netscape.javascript.JSObject;
import java.text.MessageFormat;

import java.net.URLClassLoader;
import java.net.URL;

public class WebServer extends Applet implements Runnable {

    public static ClassLoader loader = null;
    static ServerSocket socket;
    public static int localPort;

    static String onerror, onload;
    static JSObject window;
    static Thread thread;

    static String [][] params = {
	{"onerror",	"string",	"onerror javascript"},
	{"onload",	"string",	"onload javascript"},
	{"port",	"string",	"server port"},
    };

    public String [][] getParameterInfo() {
	return params;
    }
 
    public String getAppletInfo() {
	return "$Id: //depot/jhttpd/org/jhttpd/WebServer.java#3 $";
    }

    public void init() {
	window = JSObject.getWindow(this);
	onerror= getParameter( "onerror");
	onload = getParameter( "onload" );
    }

    public void start() {
	loader = getClass().getClassLoader();
	(thread = new Thread(this, "Server")).start();
    }

    public static void alert( String string ) {
	System.out.println(onload);
	System.out.println(onerror);
	if ( window != null )
	    if ( onerror != null && !"".equals(onerror) )
		window.eval( MessageFormat.format( onerror,
						   new Object[] {string} ) );
	    else
		window.eval( "alert( 'Alert: "+string+"' );" );
    }

    public synchronized void stop() {
	thread.stop();
	try {
	    socket.close();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void run() {
	String port = getParameter( "port" );
	String root = getParameter( "root" );
	if ( root == null )
	    port = "-"+port;
	else {
	    loader = null;
	    AutoLoader.srcPath = root+AutoLoader.srcPath;
	    AutoLoader.classes = root+AutoLoader.classes;
	    System.out.println( "SRC Root: "+AutoLoader.srcPath );
	}
	main( new String[] {port} );
    }

    public static int bind (String args[]) {
	int port = args != null && args.length > 0 ?
	    Integer.parseInt( args[0] ) : 8889;
	boolean isWindows = System.getProperty( "os.name" )
	    .indexOf( "Windows" ) >= 0;

	for ( int i=0 ; i<10 ; i++ ) {
	    try {
		socket = isWindows ? 
		    new ServerSocket( (port>0 ? port : -port)+i, 20,
				      InetAddress.getByName("127.0.0.1") ) :
		    new ServerSocket( (port>0 ? port : -port)+i );
		break;
	    }
	    catch ( IOException e ) {
		e.printStackTrace();
	    }
	}

	localPort = socket.getLocalPort();
	System.out.println( new java.util.Date()+
			    " Jhttpd accepting requests at "+
			    "http://localhost:"+localPort );
	return localPort;
    }

    public static void main (String args[]) {
	bind( args );
	run( args );
    }

    public static void run (String args[]) {
	int port = args != null && args.length > 0 ?
	    Integer.parseInt( args[0] ) : 8889;

	try {
	    String hostname = "localhost";
	    try {
		hostname = InetAddress.getLocalHost().getHostAddress();
	    }
	    catch ( java.net.UnknownHostException e ) {
		System.err.println( "Get hostname fails: "+e.getMessage() );
	    }

	    if ( onload != null && !"".equals(onload) )
		window.eval( MessageFormat.format( onload,
		   new Object[] {hostname, ""+localPort} ) );

	    // garbage collect every 5 minutes
	    if ( false )
		(new Thread() {
			int count;
			public void run() {
			    while ( true ) {
				try {
				    setName( "Collector["+(count++)+"]" );
				    Thread.sleep( 5*60*1000 );
				    System.gc();
				}
				catch (Exception e) {
				    e.printStackTrace();
				}
			    }
			}
		    } ).start();

	    // restricted actions
	    // for use in applets
	    new org.jhttpd.Util( false );

	    boolean classProvided = args != null && args.length > 1;
	    String container = classProvided ? args[1] :
		"javax.servlet.http.HttpServletRequest";
	    if ( loader == null )
		loader = new AutoLoader();
	    Class cl = loader.loadClass( container );
	    if ( cl == null ) {
		//Class.forName( container, true, new URLClassLoader( new URL[] {new File( "lib/jhttpd.jar" ).toURL()} ) );
		//cl = javax.servlet.http.HttpServletRequest.class;
		loader = WebServer.class.getClassLoader();
		cl = Class.forName( container, true, loader );
	    }

	    int count = 0;
	    while ( true ) {
		Socket client = socket.accept();

		if ( loader == null || port > 0 &&
		     ((AutoLoader)loader).recompiled() )
		    loader = new AutoLoader();

		if ( port > 0 )
		    cl = loader.loadClass( container );

		if ( cl == null )
		    throw new Exception( "Could not load container of class "+
					 container );

		Runnable c = (Runnable)cl.newInstance();
		Method m = cl.getMethod( "setClient",
					 new Class [] {Socket.class,
						       ClassLoader.class} );

		try {
		    m.invoke( c, new Object [] {client, loader} );
		    if ( thread != null )
			thread.setName( "Server["+(++count)+"]" );
		}
		catch (Throwable e) {
		    e.printStackTrace();
		}
	    }
	}
	catch (Exception e) {
	    alert( "Error in jhttpd main: "+e.getMessage() );
	    e.printStackTrace();
	}

    }

    public static class AutoLoader extends ClassLoader {

	Hashtable loaded = new Hashtable();
	static String srcPath = "src", classes = "classes";
	static boolean madeClasses;
	static Main compiler;

	static void makeClasses() {
	    File cdir;
	    if ( !madeClasses && !(cdir = new File( classes )).exists() )
		madeClasses = cdir.mkdir();
	}

	public class Source {
	    String name, path;
	    public File source, binary;
	    long lastLoaded;
	    public Class cl;

	    public Source( String name, AutoLoader loader ) {
		this.name = name;
		path = name.replace( '.', File.separatorChar );
		
		source = new File( srcPath, path+".java" );
		binary = new File( classes, path+".class" );

		if ( source.exists() )
		    compile();

		if ( !binary.exists() )
		    return;

		try {
		    InputStream in = new FileInputStream(binary);
		    int length = (int)binary.length();
		    byte[] classData = new byte[length];
		    int nRead, count = 0;

		    while ( length > 0 &&
			    (nRead = in.read(classData,count,length)) != -1 ) {
			count += nRead;
			length -= nRead;
		    }

		    lastLoaded = binary.lastModified();
		    cl = defineClass(name, classData, 0, classData.length);
		}
		catch (Exception e) {
		    e.printStackTrace();
		}

		loaded.put( name, this );
	    }

	    public boolean compile() {
		// System.out.println( name+" - "+loader );
		if ( /*!binary.exists() ||*/ (lastLoaded == 0 ?
		     source.lastModified() > binary.lastModified() :
		     source.lastModified() > lastLoaded) ) {

		    String spath = srcPath+File.separatorChar+path+".java";
		    System.out.println( "\nRecompiling "+spath );

		    String sep = System.getProperty( "path.separator" );
		    String cpath = System.getProperty( "java.class.path" )
			+sep+srcPath+sep+".";

		    if ( compiler == null )
			compiler = new Main();
		    // System.out.println( "Compile: "+name );

		    makeClasses();
		    compiler.compile( new String [] {"-classpath", cpath,
						     //"-target", "1.3",
						     "-d", classes,
						     // "-deprecation",
						     spath} );
		    return true;
		}
		else
		    return false;
	    }
	}

	public boolean recompiled () {
	    Enumeration keys = loaded.keys();
	    boolean recompiled = false;
	    String key;

	    while ( keys.hasMoreElements() ) {
		Source next = (Source)loaded.get( keys.nextElement() );
		recompiled = recompiled || next.compile();
	    }

	    return recompiled;
	}

	protected synchronized Class loadClass(String name, boolean resolve)
	    throws ClassNotFoundException {
	    Class c = null;
	    
	    // System.out.println( "Loading "+name );

	    try {
		c = findSystemClass( name );
	    }
	    catch (ClassNotFoundException cnf) {
		Source s = (Source)loaded.get( name );

		/*
		int sub = name.indexOf( '$' );
		if ( sub >= 0 )
		    name = name.substring( 0, sub );
		*/

		if ( s == null )
		    s = new Source( name, this );

		if ( s.lastLoaded == 0 )
		    return null;

		c = s.cl;
	    }

	    if ( resolve )
		resolveClass( c );
		
	    return c;
	}
    }
}
