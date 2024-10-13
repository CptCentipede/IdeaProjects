//this class inherits from PersonSet to organize the data in order using the imperial measuring system
public class PersonImperialSet extends PersonSet
{
    //adds people to the set using method from PersonSet
    @Override
    public void add(Person p)
    {
        super.add(p);
    }

    //converts the height from cm to in
    private double convertHeight(double height)
    {
        return height;
    }

    //converts the weight from kg to lbs
    private double convertWeight(double weight)
    {
        return weight;
    }
}
