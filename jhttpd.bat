REM
REM Batch file to run jhttpd Web Server on Windows.
REM 

REM To compile bootstrap code
REM "%JAVA_HOME%\bin\javac" -cp "lib\jhttpd.jar;%JAVA_HOME%\lib\tools.jar;." org/jhttpd/WebServer.java

REM start java process in its own window.
start/min java -cp "lib\jhttpd.jar;lib\*;%JAVA_HOME%\lib\tools.jar;." org.jhttpd.WebServer 8089

REM Wait 5 seconds
ping localhost

REM Required for client-client applet
copy lib\jhttpd.jar docs\clientclient

REM Browse to initial paage
start "%ProgramFiles%\Internet Explorer\IEXPLORE.EXE" http://localhost:8089/

