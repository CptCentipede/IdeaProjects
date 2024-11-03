/*
Name: Miles Aether
Date: 11/03/2024
Class: CSCI 2251
Purpose: To demonstrate understanding of multithreading and its usefulness in splitting larger problems into smaller
         ones as well as minimizing system resource usage

Q1: One of the goals of multi-threading is to minimize the resource usage, such as memory and processor cycles.
In three sentences, explain how multi-threaded code accomplishes this goal. Consider writing about blocking on I/O,
multicore machines, how sluggish humans are, threads compared to processes, etcetera, and connect these issues to
multi-threading.

Since they can be run at the same times, threads will complete their tasks faster, running concurrently, rather than
if they each ran one at a time. This also runs more efficiently because if your device has a 32 core CPU, that allows
for the opportunity of 32 different threads that can be used to make the process potentially around 32 times quicker to
complete. Since some threads can become blocked on certain IO operations, it is also important that the CPU switches
to another thread, so that it is not utterly idle.
*/
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class Main
{
    //should not need to use scanner for anything other than nextInt()
    public static void main(String[] args)
    {
        //create two matrices to store the data from the file

        //use try catch for file input
        try {
            //create a basic file that will be read if there are no arguments on the command prompt
            File matrixFile = new File("test.txt");

            //take an argument from the command prompt for the file name
            if (0 < args.length) {
                String filename = args[0];
                matrixFile = new File(filename);
            }

            //add file and create a scanner that will read the info from that file
            Scanner fileReader = new Scanner(matrixFile);

            //while the file has more data keep reading the data
            while (fileReader.hasNext())
            {

            }   //end of while loop

            //close the scanner that reads data from file
            fileReader.close();
        }   //end of try
        catch(FileNotFoundException e)
        {
            System.out.println("sorry your file cannot be found");
        }   //end of catch

        //create four threads that will add each quadrant concurrently
        ThreadOperation thread1 = new ThreadOperation();
        ThreadOperation thread2 = new ThreadOperation();
        ThreadOperation thread3 = new ThreadOperation();
        ThreadOperation thread4 = new ThreadOperation();

        //start the threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        //join threads so that they will complete before the rest of the program continnues
        try{
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }   //end try
        catch(InterruptedException e)
        {
            System.out.println("Interrupted");
        }   //end catch
    }   //end main

    //method that prints out the 2D array
    public void print2dArray(int[][] matrix)
    {
        //loop through the rows
        for(int i=0; i<; i++)
        {
            //loop through the columns
            for (int j = 0; j <; j++)
            {
                //print the value
                System.out.printf("");
            }   //end for loop columns
        }   //end for loop rows
    }  //end print2dArray
}   //end class