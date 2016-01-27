@ECHO OFF
SET argCount=0
SET emptyString=

GOTO :endFunctions

:: Print the usage message
:usage
	ECHO usage: %1% [ numThreads low high]
	ECHO        use without arguments to run the program with the test case values from the 
	ECHO        assignment document
	EXIT /B 0

:: run the java program with the specified arguments
:runProgram
	@java -classpath bin MyPrimeTest %1 %2 %3
	IF /I "%returnValue%" NEQ "0" GOTO :endScript
	EXIT /B 0

:: run a test case
:runTestCase
	ECHO -------------- Test Case %1% ----------------------
	ECHO No of Threads: %2%
	ECHO No of Threads: %3%
	ECHO No of Threads: %4%
	CALL :runProgram %2 %3 %4
	EXIT /B 0

:endFunctions

:: count the number of comamnd line arguments
FOR %%x in (%*) DO SET /A argCount+=1

:: check if there are zero command line arguments
IF /I "%argCount%" EQU "0" (
	:: Test Case 1
	CALL :runTestCase 1 4 1 100000
	:: Test Case 2
	CALL :runTestCase 2 4 1 1000000
	:: Test Case 3
	CALL :runTestCase 3 10 1 10000000

	GOTO :endScript 0
	)

:: check if there are three command line arguments
IF /I "%argCount%" EQU "3" (
	CALL :runProgram %1 %2 %3
	GOTO :endScript
	)

:: if we got here, there was an invalid number of command line arguments
CALL :usage %0%

:endScript