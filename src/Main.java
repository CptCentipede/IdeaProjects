/*
Name: Miles Aether
Date: 10/20/2024
Class: CSCI 2251
Purpose: Doubly-linked list that sorts itself to be used in place of an arraylist
Resources: I used the following for the insert method, but left the code as is because changing it didn't make it work
           https://www.geeksforgeeks.org/insertion-sort-doubly-linked-list/
*/

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args)
    {
        //create linkedList that will save the data from hurricane objects
        DoublyLinkedSortedList data = new DoublyLinkedSortedList();

        //use try and catch to see if file exists
        try
        {
            //add file and create a scanner that will read the info from that file
            File aceFile = new File("ace.csv");
            Scanner fileInput = new Scanner(aceFile);

            //Strip first line
            fileInput.nextLine();

            //while the file has more data keep reading the data
            while(fileInput.hasNext())
            {
                //take each line, store into an array without commas, and create array to store the data as integers
                String line = fileInput.nextLine();
                String[] values = line.split(",");
                int[] valueAsInt = new int[5];

                //loop through the array of strings and parse them into an array of integers
                for(int i=0; i<values.length; i++)
                {
                    int stringToInt = Integer.parseInt(values[i]);
                    valueAsInt[i] = stringToInt;
                }

                //create a new data of HurricaneRowData type then add it to the linkedList
                HurricaneRowData dataForRow = new HurricaneRowData(valueAsInt[0], valueAsInt[1], valueAsInt[2], valueAsInt[3], valueAsInt[4]);
                data.insert(dataForRow);
            }//while loop

            //close the scanner that reads data from file
            fileInput.close();
        }   //end try
        catch(FileNotFoundException e)
        {
            System.out.println("sorry your file cannot be found");
        }   //end catch

        //write the file output for the year with the max ACE
        try
        {
            //create a file writer and write to an output file
            FileWriter writeFile = new FileWriter("output.txt");
            int max = findAceMaxYear(data);
            String strMax = Integer.toString(max);
            writeFile.write(strMax);

            //close the file writer
            writeFile.close();
        }   //end try
        catch(IOException e)
        {
            System.out.println(e);
        }   //end catch

    }

    //method that finds the highest ACE index
    private static int findAceMaxYear(DoublyLinkedSortedList data)
    {
        //create data of type HurricaneRowData and an integer to store the value the max is at
        DoublyLinkedSortedList link = data.getFirst();
        HurricaneRowData dat = link.getValue();

        //get the year for the value
        int max_year = dat.getYear();

        //print it out
        System.out.println("Year of max ace: "+max_year);
        System.out.println("All data in order of Ace:");
        System.out.println(data);

        //return the max ace year
        return max_year;
    }   //end findAceYearMax
}   //end class