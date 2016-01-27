#! /bin/bash
if(test "$#" -ne 1);
then
	echo "usage: $0 noThreads"
	exit 0;
fi
java -classpath bin Alternation "$1"