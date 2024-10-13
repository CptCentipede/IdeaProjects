import java.util.ArrayList;

public class PersonSet implements PersonList {
    //instance variable that is protected so that the ordered set can sort the array list
    protected ArrayList<Person> people = new ArrayList<>();

    //method that adds the person to the array list if they are not already in there
    @Override
    public void add(Person p) {
        if (!people.contains(p)) {
            people.add(p);
        }
    }

    //method that gets the index of the person in the arraylist
    @Override
    public Person get(int index) {
        return people.get(index);
    }

    //toString Method that will alter the toString method in Person
    @Override
    public String toString() {
        //create a string that can be added to the return and loop through the arraylist
        String listToString = "";
        for (int i = 0; i < people.size(); i++) {
            listToString += people.get(i) + "\n";
        }

        //return the header and the contents of the arraylist
        return String.format(listToString);
    }
}
