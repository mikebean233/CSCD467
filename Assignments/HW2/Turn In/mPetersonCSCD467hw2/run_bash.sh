#! /bin/bash

function usage
{
	echo "usage: $0 [ numThreads low high]"
	echo "       use without arguments to run the program with the test case values from the "
	echo "       assignment document"
}

function runProgram
{
	java -classpath ./bin MyPrimeTest $1 $2 $3
	returnValue=$?

	if (test $returnValue -ne 0);
	then
		exit $returnValue
	fi
}

function runTestCase
{
	echo 
	echo "-------------- Test Case $1 ----------------------"
	echo "No of Threads: $2"
	echo "Min:           $3"
	echo "Max:           $4"
	echo
	runProgram $2 $3 $4
}

# Verify the correct argument count
if (test $# -ne 0) && (test $# -ne 3);
then
	usage
	exit 1
fi

if(test $# -ne 0);
then
	runProgram $1 $2 $3
	exit 0
fi

# If we got this far then there must be 3 arguments

# Test Case 1
runTestCase 1 4 1 100000

# Test Case 2
runTestCase 2 4 1 1000000

# Test Case 3
runTestCase 3 10 1 10000000


