import java.util.ArrayList;
import java.util.Collections;

//this class inherits from PersonSet to organize the data in order
public class PersonOrderedSet extends PersonSet
{
    //adds people to the set using PersonSet
    @Override
    public void add(Person p)
    {
        super.add(p);

    }

    //method that sorts the people in alphabetic order
    private void sort()
    {
        //ArrayList.sort();
    }

    //toString for the ordered set
    @Override
    public String toString()
    {
        return String.format("%-10s %10s %10s", "Name", "Height (cm)", "Weight (kg)\n" + super.toString());
    }
}
