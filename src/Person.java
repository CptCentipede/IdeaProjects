//this class will store the HR information
public class Person
{
    //instance variables
    private String name;
    private double height;
    private double weight;

    //constructor
    public Person()
    {

    }

    public Person(String name, double height, double weight)
    {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    //getters
    public String getName(){return this.name;}
    public double getHeight() {return this.height;}
    public double getWeight() {return this.weight;}

    //setters
    public void setName(String name)
    {
        this.name = name;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    //toString method to format the string
    @Override
    public String toString()
    {
        return String.format("%-10s %10.0f %10.0f", name, height, weight);
    }
}
