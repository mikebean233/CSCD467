Name: Michael Peterson

Assignment: Lab 3


Step 1) Extract mPetersonCSCD467Lab3.zip into a convenient directory

	you should get the following directory structure:

               mPetersonCSCD467Lab3/
                        |- src/
                        |   |- Alternation.java
                        |   |- Monitor.java
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
          from the mPetersonCSCD467Lab2 directory in the command prompt.

        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467Lab3 directory, then  execute 
          the command "javac src\*.java -d bin"

	

        Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467Lab3 directory, then execute the build 
          script with the command "sh build_bash.sh"

        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467Lab3 directory, then execute
          the command "javac src/*.java -d bin"

	After compilation, there should be some .class files in the mPetersonCSCD467Lab3/bin directory




Step 3) Run the application
	
        
        Windows users:
		
        - Open the command prompt, navigate to the mPetersonCSCD467Lab3 directory, then  execute the
          command "java -classpath bin Alternation”



        Linux/Mac users:
        - Start a bash shell session, navigate to the mPetersonCSCD467Lab3 directory, then execute the run script with 
          the command "sh build_bash.sh"
        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467Lab3 directory, then execute the 
          command "java -classpath bin Alternation”






--------------------------------- QUESTIONS --------------------------------------


1) Why the provided solution does NOT work? Which statement(s) cause the program to hang up? 
   
   - The original solution ends up in a deadlock because one thread is stuck in a while loop inside of a 
     
     critical area and the only way for the thread to exit the while loop is for another     
     thread to enter a critical area with the same lock, which is impossible while the first thread is stuck.

2) How did you fix that problem? 
   
   - Placed the Synchronized code inside of the while loop condition.

3) How does your changes fix the problem? ( hints: with regard to the lock ) 
   
   - It gives another thread the chance to change the isT1Turn variable by releasing the lock every while loop iteration.












