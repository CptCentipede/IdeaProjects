import java.util.ArrayList;

public class PersonSet implements PersonList
{
    //instance variable that is protected so that the ordered set can sort the array list
    protected ArrayList<Person> people = new ArrayList<>();

    //method that adds the person to the array list if they are not already in there
    @Override
    public void add(Person p)
    {

    }

    //method that gets the index of the person in the arraylist
    @Override
    public Person get(int index)
    {
        return people.get(index);   //PLACEHOLDER
    }

    //toString Method that will alter the toString method in Person
    @Override
    public String toString()
    {
        return String.format("%10s %10s %10s", "Name", "Height (cm)", "Weight (kg)"+super.toString());
    }
}
