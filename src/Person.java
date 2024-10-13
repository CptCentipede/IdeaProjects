//this class will store the HR information
public class Person implements Comparable<Person>
{
    //instance variables
    private String name;
    private double height;
    private double weight;

    //constructor
    public Person(String name, double height, double weight)
    {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }   //end constructor

    //compare the contents of two objects for PersonSet
    @Override
    public boolean equals(Object o)
    {
        //if Object o is null then return false
        if(o == null) {return false;}

        //if Object o == this then return true
        if(o == this) {return true;}

        //if Object o is not an instance of Person then return false
        if(!(o instanceof  Person)) {return false;}

        //Declare a new variable of type Person (perhaps named p) and assign it to Object o cast as type Person
        Person p = (Person) o;

        //if Person p has the same name, height, and weight as this then return true else return false
        return name.equals(p.name)
                && Double.compare(height, p.height)==0
                && Double.compare(weight, p.weight)==0;
    }   //end of equals method

    //compares names to be sorted
    @Override
    public int compareTo(Person p)
    {
        return this.name.compareTo(p.name);
    }   //end compareTo

    //getters
    public String getName()
    {return this.name;}

    public double getHeight()
    {return this.height;}

    public double getWeight()
    {return this.weight;}

    //setters
    public void setName(String name)
    {this.name = name;}

    public void setHeight(double height)
    {this.height = height;}

    public void setWeight(double weight)
    {this.weight = weight;}

    //toString method to format the string
    @Override
    public String toString()
    {
        if(height % 1 != 0 || weight % 1 != 0)
        {
            return String.format("%-10s %10.2f %10.2f", name, height, weight);
        }   //end of if
        else
        {
            return String.format("%-10s %10.0f %10.0f", name, height, weight);
        }   //end of else
    }   //end of toString
}   //end of class
