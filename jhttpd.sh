#! /bin/sh -f
#
# Script to run jhttpd web server on UNIX
#

PORT=${1:-8089}

cd `dirname $0`

export JAVA_HOME=${JAVA_HOME:?environment variable JAVA_HOME must be set}

# classes dir must not be in CLASSPATH for auto-compile of servlet code
export CLASSPATH=`bash -c 'echo lib/*.{jar,zip} | sed -e "s/ /:/g"'`:.:$JAVA_HOME/lib/tools.jar

 echo "Using CLASSPATH: $CLASSPATH"

BOOT=org/jhttpd/WebServer
if test -e $BOOT.java -a ! -e $BOOT.class ; then # -target 1.1 q
  echo "Recompiling bootstrap code..."
  $JAVA_HOME/bin/javac -classpath $CLASSPATH $BOOT.java
  # recompiling requires "java40.jar" with applet api in ./lib
fi

# for java 6 and above you can do this ....
#export CLASSPATH="lib/*:.:$JAVA_HOME/lib/tools.jar"
$JAVA_HOME/bin/java -cp $CLASSPATH org.jhttpd.WebServer $PORT

# -Xss50M
