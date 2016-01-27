Name: Michael Peterson


Assignment: Homework 2


Step 1) Extract mPetersonCSCD467HW2.zip into a convenient directory

	you should get the following directory structure:

               mPetersonCSCD467HW2/
                        |- src/
                        |   |- Counter.java
                        |   |- MyPrimeTest.java
                        |   |- SerialPrime.java
                        |   |- ThreadPrime.java
                        |
                        |- bin/
                        |
                        |- build_windows.bat
                        |- build_bash.sh
                        |- run_windows.bat
                        |- run_bash.sh



Step 2) Compile the application:
	
        
        Windows users:
		
        - Run the batch file build_windows.bat by double clicking on the file, or typing build_windows.bat 
          from the mPetersonCSCD467HW2 directory in the command prompt.

        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467HW2 directory, then  execute 
          the command "javac src\*.java -d bin"

	

        Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467HW2 directory, then execute the build 
          script with the command "sh build_bash.sh"

        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467HW2 directory, then execute
          the command "javac src/*.java -d bin"

	After compilation, there should be some .class files in the 
          mPetersonCSCD467HW2/bin directory




Step 3) Run the application

	Note: The command line arguments numThreads, min, and max can be applied to both scripts, if arguments are ommitted then the test cases from the 
               assignment description will be used automatically.
        
        Windows users:
		
        - Run the batch file run_windows.bat by double clicking on the file, or running the command "run_windows.bat [ numThreads min max ]" from the 
          mPetersonCSCD467HW2 directory in the command prompt.
        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467HW2 directory, then  execute the
          command "java -classpath bin MainWindow numThreads min max"



        Linux/Mac users:
        - Start a bash shell session, navigate to the mPetersonCSCD467HW2 directory, then execute the run script with 
          the command "sh build_bash.sh [ numThreads min max ]"
        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467HW2 directory, then execute the 
          command "java -classpath bin MainWindow numThreads min max"
