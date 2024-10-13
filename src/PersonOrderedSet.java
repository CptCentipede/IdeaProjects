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
    }
}
