//this class inherits from PersonSet to organize the data in order using the imperial measuring system
public class PersonImperialSet extends PersonSet
{
    //adds people to the set using method from PersonSet
    @Override
    public void add(Person p)
    {
        super.add(p);
        convertHeight(p.getHeight());
        convertWeight(p.getWeight());
    }

    //converts the height from cm to in
    private double convertHeight(double height)
    {return height/2.54;}

    //converts the weight from kg to lbs
    private double convertWeight(double weight)
    {return weight*2.2;}

    //toString for the imperial set
    @Override
    public String toString()
    {
        return String.format("%-10s %10s %10s", "Name", "Height (in)", "Weight (lbs)\n" + super.toString());
    }
}
