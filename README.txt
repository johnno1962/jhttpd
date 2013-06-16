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

================================
 jhttpd.jar - applet web server
================================

This jar contains sources and compiled class files for jhttpd
applet web server for use in a web site. To use for application
development unzip this jar into a directory (jhttpd for example)
and place new any class source required in the "./src" directory
and any library jars in a "./lib" directory. You can then run the
server using the script file "jhttpd.sh". The server will then
recompile classes as they change and reload them automatically.

Classes compiled automatically will be written to the "./classes"
directory which should not be in the CLASSPATH. Also, any classes
in jars in the lib directory will not be recompiled. To develope
the server itself remove the ./javax directory after extraction
and make sure jhttpd.jar is not in ./lib or the CLASSPATH.

Go to http://www.jhttpd.org for updates and documentation.
See the server in use at dbexplorer.com and dbeclipse.org.

(c) Dynamic Browser Applications Ltd, July 2007


