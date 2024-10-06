//this is an interface that will allow other classes to use this as a point of interaction between
//hardware and software
public interface PersonList
{
    public void add(Person p);
    public Person get(int index);
}
