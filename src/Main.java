/*
Name: Miles Aether
Date: 10/09/2024
Class: CSCI 2251
Purpose: This program will take in a file name from the command line to take in a file that will then be organized
         in order, without duplicates, to be written to an output for both the measurements given and converted into
         the imperial measuring system
Resources: I used the following to get the filename from the command line argument
           https://stackoverflow.com/questions/16802147/java-i-want-to-read-a-file-name-from-command-line-then-use-a-bufferedreader-to
           I used the following to understand the compareTo method where the class implements Comparable
           https://java-programming.mooc.fi/part-10/2-interface-comparable
           I used the following to figure out how to return a string with two decimals if the decimals after height or weight were not zero
           https://stackoverflow.com/questions/14799943/how-to-check-if-number-is-a-decimal
*/

/*
Q1: Car and Engine are related by which, Inheritance or Composition?
A car has an engine, so composition

Q2: Color and Red are related by which, Inheritance or Composition?
Red is a color, so inheritance

Q3: Shirt and Clothing are related by which, Inheritance or Composition?
Shirt is a type of clothing, so inheritance

Q4: Furniture and Desk are related by which, Inheritance or Composition?
Desk is a type of furniture, so inheritance

Q5: CellPhone and Battery are related by which, Inheritance or Composition?
A cellphone has a battery, so composition
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    public static void main(String[] args)
    {
        //instantiate imperial and ordered classes
        PersonList imperialSet = new PersonImperialSet();
        PersonList orderedSet = new PersonOrderedSet();

        //use try catch for file input
        try {
            //create a basic file that will be read if there are no arguments on the command prompt
            File hrFile = new File("test.txt");

            //take an argument from the command prompt for the file name
            if (0 < args.length) {
                String filename = args[0];
                hrFile = new File(filename);
            }

            //add file and create a scanner that will read the info from that file
            Scanner fileReader = new Scanner(hrFile);

            //Strip first line
            fileReader.nextLine();

            //while the file has more data keep reading the data
            while (fileReader.hasNext()) {
                //take each line, store into an array without commas, and create array to store the data as integers
                String name = fileReader.next();
                double height = fileReader.nextDouble();
                double weight = fileReader.nextDouble();


                //create an object of type Person for the imperial and ordered set
                Person imperialPerson = new Person(name, height, weight);
                Person orderedPerson = new Person(name, height, weight);

                //add the person to the imperial and ordered classes
                imperialSet.add(imperialPerson);
                orderedSet.add(orderedPerson);
            }   //end of while loop

            //Collections.sort(imperialSet);
            //print imperial and ordered sets to command line
            System.out.println(imperialSet.toString()+"\n");
            System.out.println(orderedSet.toString());

            //close the scanner that reads data from file
            fileReader.close();
        }   //end of try
        catch(FileNotFoundException e)
        {
            System.out.println("sorry your file cannot be found");
        }   //end of catch

        //write a file for output using try catch
		try
		{
            //write a file for the PersonImperialSet
			FileWriter writeImperial = new FileWriter("hr_imperial_set_output.txt");
            writeImperial.write(imperialSet.toString());
            writeImperial.close();

            //write a file for the PersonOrderedSet
            FileWriter writeOrdered = new FileWriter("outputhr_ordered_set_output.txt");
            writeOrdered.write(orderedSet.toString());
            writeOrdered.close();
		}   //end of try
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}   //end of catch
    }   //end of main
}   //end of class
