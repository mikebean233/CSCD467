Name: Michael Peterson
Assignment: Homework 3

NOTE: I implemented the extra credit.  The queue class that I wrote is a linked based queue and I made a lock for the front and rear nodes 
      in the list.


Step 1) Extract mPetersonCSCD467HW3.zip into a convenient directory

	you should get the following directory structure:

               mPetersonCSCD467HW3/
                        |- src/
                        |   |- ParallelSearchCoarse.java
                        |   |- Reader.java
                        |   |- Searcher.java
                        |   |- SerialSearchPattern.java
                        |   |- SharedQueue.java
                        |   |- SharedQueueTester.java
                        |
                        |- bin/
                        |
                        |- build_bash.sh
                        |- run_bash.sh



Step 2) Compile the application:
	
        
  Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467HW3 directory, then execute the build 
          script with the command "sh build_bash.sh"

        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467HW3 directory, then execute
          the command "javac src/*.java -d bin"

	After compilation, there should be some .class files in the 
          mPetersonCSCD467HW3/bin directory




Step 3) Run the application

	Note: The script will automatically run the serial and parallel solution for comparison.  The command line arguments 
              
              FileName and Pattern can be applied.
 
	usage: sh build_bash.sh FileName Pattern

        Linux/Mac users:
        - Start a bash shell session, navigate to the mPetersonCSCD467HW3 directory, then execute the run script with 
          the command "sh build_bash.sh FileName Pattern”
        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467HW3 directory, then execute the 
          command "java -classpath bin SerialSearchPattern FileName Pattern” for the serial solution and 
          
          “java -classpath bin ParallelSearchCoarse FileName Pattern”
