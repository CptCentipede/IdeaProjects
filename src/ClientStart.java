/*
Name: Miles Aether
Date: 1 December 2024
Class: CSCI 2251
Purpose: Starts the client when compiled and ran
*/

import javax.swing.JFrame;

public class ClientStart
{
    public static void main(String[] args)
    {
        //create the client and set its default to close after pressing the x
        Client c = new  Client();
        c.runClient();
    }
}