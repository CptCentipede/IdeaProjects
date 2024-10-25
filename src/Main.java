import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //instantiate imperial and ordered classes
        //SortedTreeSetInterface imperialSet = new SortedTreeSet();
        SortedTreeSetInterface orderedSet = new SortedTreeSet();

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
                //Person imperialPerson = new Person(name, height, weight);
                Person newPerson = new Person(name, height, weight);

                //add the person to the imperial and ordered classes
                //imperialSet.add(imperialPerson);
                orderedSet.add(newPerson);
            }   //end of while loop

            //print imperial and ordered sets to command line
            //System.out.println(imperialSet.toString()+"\n");
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
            /*FileWriter writeImperial = new FileWriter("hr_imperial_set_output.txt");
            writeImperial.write(imperialSet.toString());
            writeImperial.close();*/

            //write a file for the PersonOrderedSet
            FileWriter writeOrdered = new FileWriter("tree_output.txt");
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
