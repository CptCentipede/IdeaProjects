/*
Name: Miles Aether
Date: 1 December 2024
Class: CSCI 2251
Purpose: Server that currently takes in data from the client GUI to print out either a string or matrix
*/

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    //instance variables
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;
    private final int portNumber = 12345;
    private final int backlogLimit = 100;

    // set up and run server
    public void runServer()
    {
        try // set up server to receive connections; process connections
        {
            //backlog is how many clients can wait in the queue
            server = new ServerSocket(portNumber, backlogLimit);

            while (true)
            {
                try
                {
                    waitForConnection();
                    getStreams();
                    processConnection();
                }
                catch (EOFException e)
                {
                    System.out.println("\nServer terminated connection");
                }
                finally
                {
                    closeConnection();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // wait for connection to arrive, then display connection info
    private void waitForConnection() throws IOException
    {
        System.out.println("Waiting for connection\n");

        connection = server.accept();

        System.out.println("New connection received from: " + connection.getInetAddress().getHostName());
    }

    // get streams to send and receive data
    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());
    }

    // process connection with client
    private void processConnection() throws IOException
    {
        //instantiate the message and set to null
        Object message = null;

        do
        {
            try
            {
                //read message from client
                message = input.readObject();

                //if the message is a String print it out
                if(message instanceof String)
                {
                    //cast to string and print
                    String data_from_client = (String)message;
                    System.out.println("\nCLIENT>>>" + data_from_client);
                    System.out.println();
                }

                //if the message is a 2darray loop through the array and print it out
                if(message instanceof int[][])
                {
                    int[][] matrix1 = (int[][]) message;
                    int[][] matrix2 = (int[][]) input.readObject();

                    for(int[] row: matrix1)
                    {
                        for(int element: row) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    for(int[] row: matrix2)
                    {
                        for(int element: row) {
                            System.out.print(element + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                    int[][] matrixResult = new int[matrix1.length][matrix1[0].length];

                    //create references to the four quadrants
                    String quad1 = "First Quadrant";
                    String quad2 = "Second Quadrant";
                    String quad3 = "Third Quadrant";
                    String quad4 = "Fourth Quadrant";

                    //create four threads that will add each quadrant concurrently
                    ThreadOperation thread1 = new ThreadOperation(matrix1, matrix2, matrixResult, quad1);
                    ThreadOperation thread2 = new ThreadOperation(matrix1, matrix2, matrixResult, quad2);
                    ThreadOperation thread3 = new ThreadOperation(matrix1, matrix2, matrixResult, quad3);
                    ThreadOperation thread4 = new ThreadOperation(matrix1, matrix2, matrixResult, quad4);

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

                    //print in server
                    print2dArray(matrixResult);

                    //send the matrix to the client
                    sendData(matrixResult);
                }   //end if
            }   //end try
            catch (ClassNotFoundException e)
            {
                System.out.println("\nUnknown object type received");
            }

            //Unless client requests terminate, reply.
            if(!(message instanceof String && ((String)message).equals("TERMINATE") ))
            {
				/* Send the same message back to 
				the client every time. This
				prevents the Client from hanging
				on this line of code:
				message = (String) input.readObject(); */
                sendData("OK, Client\n");
            }
        } while (!(message instanceof String && ((String)message).equals("TERMINATE") ));
		
		/* Send this so the client has
		confirmation that the connection 
		should be terminated. */
        sendData("TERMINATE");
    }   //end processConnection

    // close streams and socket
    private void closeConnection()
    {
        System.out.println("\nTerminating connection\n");
        try
        {
            output.close(); // close output stream
            input.close(); // close input stream
            connection.close(); // close socket
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // send message to client
    private void sendData(Object message)
    {
        try
        {
            output.writeObject(message);
            output.flush();
        }
        catch (IOException e)
        {
            System.out.println("\nError writing object");
        }
    }   //end sendData

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
            //print a line, so the next row can print below
            System.out.println();
        }   //end for loop rows
    }  //end print2dArray
}   //end class