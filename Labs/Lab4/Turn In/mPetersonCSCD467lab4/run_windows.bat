@ECHO OFF
SET argCount=0

:: count the number of arguments
FOR %%x in (%*) DO SET /A argCount+=1

:: check argument count
IF /I "%argCount%" EQU "1" (
	java -classpath bin Alternation %1
	GOTO :endScript
	)

:: no arguments
ECHO "usage: run_windows.bat noThreads"

:endScript
EXIT /B 0