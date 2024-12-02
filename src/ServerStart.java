/*
Name: Miles Aether
Date: 1 December 2024
Class: CSCI 2251
Purpose: Starts the server when compiled and ran
*/

public class ServerStart
{
    public static void main(String[] args)
    {
        //create new server obeject and run it
        Server my_server = new Server();
        my_server.runServer();
    }
}
