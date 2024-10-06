/*
Name: Miles Aether
Date: 10/09/2024
Class: CSCI 2251
Purpose: This program will take in a file name from the command line to take in a file that will then be organized
         in order, without duplicates, to be written to an output for both the measurements given and converted into
         the imperial measuring system
Resources: I used the following to get the filename from the command line argument
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

public class Main
{
    public static void main(String[] args)
    {
        //instantiate objects of type Person and PersonSet for testing
        Person personObject = new Person("Billy", 29, 123);
        PersonSet personSetObject = new PersonSet();

        //test for personObject and personSetObject
        System.out.println(personObject.toString());
        System.out.println();

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

                //create a new data of HurricaneRowData type then add it to the arrayList
                Person newPerson = new Person(name, height, weight);
                System.out.println(newPerson.toString());
                //PersonSet.add(newPerson);

            }   //end of while loop

            //close the scanner that reads data from file
            fileReader.close();
        }   //end of try
        catch(FileNotFoundException e)
        {
            System.out.println("sorry your file cannot be found");
        }   //end of catch

		try
		{
			FileWriter fileWriterOrder = new FileWriter("outputfile.txt");
			fileWriterOrder.write("testing");
			fileWriterOrder.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}
    }
}
