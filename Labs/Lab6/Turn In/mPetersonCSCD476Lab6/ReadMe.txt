﻿Name: Michael Peterson

Assignment: Lab 6

Step 1) Extract mPetersonCSCD467Lab6.zip into a convenient directory

	you should get the following directory structure:

               mPetersonCSCD467Lab6/
                        |- src/
                        |   |- BrokenBarrierException.java
                        |   |- CyclicBarrier.java
                        |   |- LastCommand.java
                        |   |- MyBarrierTester.java
                        |   |- Worker.java
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
          from the mPetersonCSCD467Lab6 directory in the command prompt.

        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467Lab6 directory, then  execute 
          the command "javac src\*.java -d bin"

	

        Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467Lab6 directory, then execute the build 
          script with the command "sh build_bash.sh"

        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467Lab6 directory, then execute
          the command "javac src/*.java -d bin"

	After compilation, there should be some .class files in the mPetersonCSCD467Lab6/bin directory




Step 3) Run the application
	        
        Windows users:
      
        - Run the batch file run_windows.bat by double clicking on the file, or typing run_windows.bat from the 
          mPetersonCSCD467Lab6 directory in the command prompt.
        
        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467Lab6 directory, then  execute the
          command "java -classpath bin MyBarrierTester” 

        Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467Lab6 directory, then execute the run script with 
          the command "sh run_bash.sh"
        
        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467Lab6 directory, then execute the 
          command "java -classpath bin MyBarrierTester”

