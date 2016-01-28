Name: Michael Peterson

Assignment: Lab 5

======================================  QUESTIONS  ==========================================================================

1) Read and understand the provided RandomCharacters.java program. The RandomCharacters
   class is a Java Applet. You are required to add a main method into this class, so that
   RandomCharacters could also be run as a Java Application. (hint: in main method you could
   add a JFrame in which you run the applet.)


2) Three threads are created in the program, what does each thread do?
   How each thread is associated with certain group of GUI components?

   - Each thread is responsible for generating a random letter and displaying that random letter
     to the threads associated row in the interface (when not in suspended mode).  The program 
     defines arrays of JLabels, and Checkboxes.  The id associated with the currently running 
     thread correlates to an index in each of these arrays.


3) When(under what condition) does a thread go sleep? When (under what condition) does it wake up?

   - A thread only goes to sleep after the call to Thread.sleep() in run(), the conditional for the while loop
     that this statement is in only allows it to be reached if the reference for the thread in the 
     array is not null (in other words stop() hasn't been called).  The Thread comes out of sleep after the 
     requested time has elapsed and the scheduler continues it's execution eventually.

     As far as being "suspended", that happens when a user clicks a checkbox, which triggers the actionPerformed 
     handler, at which time the threads flag in the suspended array is toggled.  If the value was
     just toggled to true then notify() is called on the RandomCharacter instance and a thread is chosen from the
     wait set to be placed in the entry set.  Hopefully this chosen thread will be the one that is associated with
     the checkbox that the user clicked on ... Threads that do not have their suspended flag set to true will end 
     up getting stuck in the waiting state in the run() method until their suspended flag is toggled and they are
     woken.


4) Run the program as Java application. You must identify at least one defect (the program does not 
   behave as we expected at one point.) for this program. Please describe the defect and fix it by 
   modifying the program.

   - The defect I noticed is that when the user unchecks a checkbox and there is more then one suspended thread,
     the thread doesn't necessarily get woken up.  This is because the program is using notify() to wake up a 
     random thread from the wait set, if this doesn't happen to be the correct thread, the thread that was woken
     will just be put right back into the waiting state again.  The user has to keep trying the checkbox until 
     the correct thread is chosen by the scheduler, it looks like the chances of the correct thread actually being
     chosen is 1 / (the number of waiting threads).  

     To correct the problem, I simply changed notify() to notifyAll().  This guarantees that one of the threads that
     are woken is the correct one, the rest just get put back into the waiting state. 

5) Add a JButton in your GUI window (may be a JFrame) which shows a text of “Stop”. When we click the button, all 
   threads are stopped then terminated. You are required to use the existing stop() method in RandomCharacters class.


6) Please explain why the stop() method you used could terminate all threads? In what ways this approach of terminating 
   threads differs from what we learned before? Describe the pros and cons when using this approach to terminate threads. 
   Under what circumstances we could use this approach, and sometimes we could not?

   - stop() causes all of the threads to terminate by making all of the thread references in the threads array null and waking all of the 
     threads with nofityAll().  This causes each thread to return from wait, fail the while conditional (specifically 
     "threads[index] == currentThread") because it is now comparing null to an object, then fail the same conditional in the outer loop, 
     which allows it to reach the end of the run() method and terminate.

     Before we were terminating a thread by causing an interrupt on that thread, which would throw an exception and cause the thread to stop
     stop the work its currently doing by exiting some block of code and the run method.

     This time we are using a flag a variable (in the form of a Thread reference) to indicate whether we are finished with a thread or not.
     This approach doesn't require us to catch exceptions or keep calling the interrupted method on the thread.

     This approach might be a problem if having the reference to the thread for little longer might be desirable.


==========================================================================================================================================


Step 1) Extract mPetersonCSCD467Lab5.zip into a convenient directory

	you should get the following directory structure:

               mPetersonCSCD467Lab5/
                        |- src/
                        |   |- RandomCharacters.java
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
          from the mPetersonCSCD467Lab5 directory in the command prompt.

        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467Lab5 directory, then  execute 
          the command "javac src\*.java -d bin"

	

        Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467Lab5 directory, then execute the build 
          script with the command "sh build_bash.sh"

        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467Lab5 directory, then execute
          the command "javac src/*.java -d bin"

	After compilation, there should be some .class files in the mPetersonCSCD467Lab5/bin directory




Step 3) Run the application
	        
        Windows users:
      
        - Run the batch file run_windows.bat by double clicking on the file, or typing run_windows.bat from the 
          mPetersonCSCD467Lab5 directory in the command prompt.
        
        - Alternatively, open the command prompt, navigate to the mPetersonCSCD467Lab5 directory, then  execute the
          command "java -classpath bin RandomCharacters” 

        Linux/Mac users:

        - Start a bash shell session, navigate to the mPetersonCSCD467Lab5 directory, then execute the run script with 
          the command "sh build_bash.sh"
        
        - Alternatively, start a bash shell session, navigate to the mPetersonCSCD467Lab5 directory, then execute the 
          command "java -classpath bin RandomCharacters”

