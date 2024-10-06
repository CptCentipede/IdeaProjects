/*
Name: Miles Aether
Date: 10/09/2024
Class: CSCI 2251
Purpose: This program will take in a file name from the command line to take in a file that will then be organized
         in order, without duplicates, to be written to an output for both the measurements given and converted into
         the imperial measuring system
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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        System.out.println();

		/*
		// Don't overcomplicate the data
		// reading. After skipping the
		// first row, you can use the
		// following to read a row of
		// character info, assuming your
		// Scanner is named "fileReader"
		String name = fileReader.next();
		double height = fileReader.nextDouble();
		double weight = fileReader.nextDouble();
		*/



		/*try
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
		}*/
    }
}
