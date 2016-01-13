#! /bin/bash
which javac > /dev/null 2> /dev/null
returnValue=$?

if test "$returnValue" -ne "0"
	then
		echo "Error: The java compiler \"javac\" does not appear to be installed on your system" 1>&2;
		exit $returnValue;
fi

javac src/*.java -d ./bin
