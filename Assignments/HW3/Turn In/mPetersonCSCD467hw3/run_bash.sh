#! /bin/bash

function usage
{
	echo "usage: $0 FileName Pattern"
	echo "       use without arguments to run the program with the test case values from the "
	echo "       assignment document"
}

function runSerialProgram
{
	echo "-------------- Serial Version ----------------------"
	echo "filename: $1"
	echo "pattern: $2"

	java -classpath ./bin SerialSearchPattern "$1" "$2"
	returnValue=$?
	echo
	if (test $returnValue -ne 0);
	then
		exit $returnValue
	fi
}

function runParallelProgram
{
    echo "-------------- Parallel Version ----------------------"
	echo "filename: $1"
	echo "pattern: $2"
	
    java -classpath ./bin ParallelSearchCoarse "$1" "$2"
	returnValue=$?
	echo
	if (test $returnValue -ne 0);
	then
		exit $returnValue
	fi

}


# Verify the correct argument count
if (test $# -ne 2);
then
	usage
	exit 1
fi

if(test $# -ne 0);
then
	runSerialProgram "$1" "$2"
	runParallelProgram "$1" "$2"
	exit 0
fi

