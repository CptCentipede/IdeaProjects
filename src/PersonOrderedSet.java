//this class inherits from PersonSet to organize the data in order
import java.util.ArrayList;
import java.util.Collections;

public class PersonOrderedSet extends PersonSet
{
    //adds people to the set using PersonSet
    @Override
    public void add(Person p)
    {
        //sort the ArrayList
        sort(people);

        //refer to parent class
        super.add(p);
    }

    //method that sorts the people in alphabetic order
    private void sort(ArrayList<Person> people)
    {
        //sort the ArrayList using Collections.sort()
        Collections.sort(people);
    }

    //toString for the ordered set that uses toString from parent class
    @Override
    public String toString()
    {
        return String.format("%-10s %10s %10s", "Name", "Height (cm)", "Weight (kg)\n" + super.toString());
    }
}
