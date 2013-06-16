![Icon](http://injectionforxcode.johnholdsworth.com/jhttpd.gif) 

# jhttpd - Java Web Micro-Server

Copyright (c) John Holdsworth 2000-2013

jhttpd is a Web Server written by a Perl programmer learning Java back in the days of Java 1.1 which
might help explain some of the code! It's too late to refactor the code now but it does however 
have some unusual features which I've found handy.

	jhttpd runs servlets using the first part of the path as the servlet class name
	jhttpd recompiles classes in the jhttpd/src directory automatically on demand
	jhttpd will also run CGI scripts using the example src/cgi_bin.java servlet
	jhttpd servlets have a cancel method for when the page load is cancelled
	jhttpd server can run as a signed applet inside a brower (a.k.a client client)
	jhttpd can be bundled inside windows executables using jni for standalone web apps
	jhttpd can be embedded inside an eclipse plugin to code a web based plugin interface

## Using jhttpd.

On UNIX run the script jhttpd/jhttpd.sh and browse to the address shown. Your JAVA_HOME
environment variable must point to a Java JDK. On windows use jhttpd.bat. Create your
standard servlets in the jhttpd/src directory and they will be recompiled on demand
as you make edits in a manner more akin to a CGI script. Conventional CGI scripts can 
also be run if you have Perl and scripts in the docs/cgi-bin directory.

If you know how to codesign a jar the server can be run up inside the browser itself.
An example of how to do this is in the directory docs/clientclient. This was used to
create web applications that don't require a server to run, running entirely in the
client browser. An example of this can be found at [http://dbexplorer.com](http://dbexplorer.com)
which you should find still runs in Internet Explorer if you accept the security prompts.

An example of how jhttpd can be bundled inside a windows executable is provided in
the [dbexplorer](https://github.com/johnno1962/dbexplorer) Visual Studio project. 
The executable produced uses JNI to run jhttpd in a thread to serve pages to the 
windows form Browser object in the application. The jars and the loadjava.dll are 
compiled into the app as resources and copied into a temporary directory so they 
can be used by the app.

Finally the source required to use jhttpd in a eclipse plugin can be viewed in the
[dbeclipse](https://github.com/johnno1962/dbeclipse) project. This provides a means
of writing an elcipse plugin with a Web based gui serving pages from an internal
server run up by the plugin. The plugin uses the redistributable versions of the
Oracle, Sybase and IBM DB2 JDBC drivers which you will have to source separately.

## Bug reports

Please file bug reports to [jhttpd@johnholdsworth.com](mailto:jhttpd@johnholdsworth.com)
or fork and enter pull requests with any improvements on github in the usual way.

## Please note:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

