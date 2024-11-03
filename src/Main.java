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

            //read the dimensions for the number of rows and columns
            int numRows = fileReader.nextInt();
            int numCols = fileReader.nextInt();

            //create two matrices and store data from file using matrixFromFile method
            int[][] matrix1 = matrixFromFile(numRows, numCols, fileReader);
            int[][] matrix2 = matrixFromFile(numRows, numCols, fileReader);

            //test if matrices were populated correctly
            System.out.println("First matrix in file");
            print2dArray(matrix1);
            System.out.println("\nSecond matrix in file");
            print2dArray(matrix2);

            //create references to the four quadrants
            String quad1 = "First Quadrant";
            String quad2 = "Second Quadrant";
            String quad3 = "Third Quadrant";
            String quad4 = "Fourth Quadrant";

            //create four threads that will add each quadrant concurrently
            ThreadOperation thread1 = new ThreadOperation(matrix1, matrix2, quad1);
            ThreadOperation thread2 = new ThreadOperation(matrix1, matrix2, quad2);
            ThreadOperation thread3 = new ThreadOperation(matrix1, matrix2, quad3);
            ThreadOperation thread4 = new ThreadOperation(matrix1, matrix2, quad4);

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

            //close the scanner that reads data from file
            fileReader.close();
        }   //end of try
        catch(FileNotFoundException e)
        {
            System.out.println("sorry your file cannot be found");
        }   //end of catch

        //test matrix
        int[][] testMatrix =
        {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        //print test matrix
        System.out.println("\nTest matrix");
        print2dArray(testMatrix);
    }   //end main

    //method that prints out the 2D array
    public static void print2dArray(int[][] matrix)
    {
        //loop through the rows
        for(int i=0; i< matrix.length; i++)
        {
            //loop through the columns
            for (int j = 0; j<matrix[i].length; j++)
            {
                //print the value
                System.out.printf("%d ", matrix[i][j]);
            }   //end for loop columns
            System.out.println();
        }   //end for loop rows
    }  //end print2dArray

    public static int[][] matrixFromFile(int rows, int columns, Scanner file_reader)
    {
        //create a matrix to be returned
        int[][] tempMatrix = new int[rows][columns];

        //populate the matrix
        //first the rows
        for(int i=0; i<rows; i++)
        {
            //then the columns
            for(int j=0; j<columns; j++)
            {
                //put the next integer at index [i][j]
                tempMatrix[i][j] = file_reader.nextInt();
            }   //end for loop cols
        }   //end for loop rows

        //return the matrix
        return tempMatrix;
    }  //end matrixFromFile
}   //end class