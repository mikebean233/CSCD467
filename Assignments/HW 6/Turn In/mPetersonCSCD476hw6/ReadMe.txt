Name: Michael Peterson
Assignment: Homework 6

Step 1) Extract mPetersonCSCD467hw6.zip into a convenient directory on the hadoop cluster

	you should get the following directory structure:

               mPetersonCSCD467hw6/
                        |- src/
                        |   |- WordCount.java
                        |
                        |- input
                        |   |- file1
                        |   |- file2
                        |
                        |- output
                        |   |- output_luck.txt
                        |   |- output_world.txt
                        |
                        |- bin/
                        |
                        |- build.sh
                        |- copy_input_files.sh
                        |- copy_output_files.sh
                        |- do_all.sh
                        |- remove_output_files.sh
                        |- run.sh
                        |- ReadMe.txt
                        |- ScreenShot.txt



Step 2) Compile and run the application

    You can just run the script do_all.sh <yourDirectory> where yourDirectory is the path in the HDFS where the 
    input and output directories are located, in my case I used /user/mpeterson10/wc .

    The script will build the program, create the jar file, remove the output dir in the hdfs, run the program, copy the 
    output to the local fs, then display the contents of the output file to the screen.  The last 4 steps are repeated twice,
    once using “world” as the pattern, and once using “luck” as the pattern.

	
    Alternatively, you can manually enter all of the commands.
    
    A) Build the program:        hadoop com.sun.tools.javac.Main ./src/WordCount.java -d ./bin
    B) Create the jar file:      jar -cvf ./bin/wc.jar -C ./bin/ .
    
    if the output directory exists
    C) Remove output directory:  hadoop fs -rm -r <yourDirectory>/output
  
    D) Run the program:          hadoop jar ./bin/wc.jar WordCount <pattern> <yourDirectory>/input <yourDirectory>/output
    
    make sure the local directory ./output/new exists, and is empty

    E) Copy output to the local fs: hadoop fs -copyToLocal <yourDirectory>/output/* ./output/new  
    F) View the Contents of the output files: cat ./output/new/*