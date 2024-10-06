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

    //getters
    public double getHeight() {return this.height;}
    public double getWeight() {return this.weight;}

    //setters
    public void setHeight(double height)
    {

    }

    public void setWeight(double weight)
    {

    }

    //toString method to format the string
    @Override
    public String toString()
    {
        return name+" "+height+" "+weight;
    }
}
