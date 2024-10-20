public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface
{
    //instance variables
    private HurricaneRowData value = null;
    private DoublyLinkedSortedList next = null;
    private DoublyLinkedSortedList previous = null;

    //Get the value of the current DoublyLinkedSortedList
    public HurricaneRowData getValue()
    {
        return value;
    }

    //Return true if next is not null
    public boolean hasNext() {
        if (next == null) {
            return false;    //PLACEHOLDER
        }   //end if

        return true;
    }   //end hasNext

    //Set next to be the given DoublyLinkedSortedList
    public void setNext(DoublyLinkedSortedList next)
    {
        this.next = next;
        next.previous = this;
    }

    //Return a reference to the next DoublyLinkedSortedList
    public DoublyLinkedSortedList getNext()
    {
        return next;
    }

    //Return true if previous is not null
    public boolean hasPrevious()
    {
        return true;    //PLACEHOLDER
    }

    //Set previous to be the given DoublyLinkedSortedList
    public void setPrevious(DoublyLinkedSortedList previous)
    {
    }

    //Return a reference to the previous DoublyLinkedSortedList
    public DoublyLinkedSortedList getPrevious()
    {
        return previous;
    }

    //Return a reference to the first DoublyLinkedSortedList element in the list
    public DoublyLinkedSortedList getFirst()
    {
        return ;
    }

    //Return a reference to the last DoublyLinkedSortedList element in the list
    public DoublyLinkedSortedList getLast()
    {
        return ;
    }

    //Remove the DoublyLinkedSortedList element that has toRemove as its value
    public DoublyLinkedSortedList remove(HurricaneRowData toRemove)
    {
        return ;
    }

    //Insert a new DoublyLinkedSortedList element that has the given newValue in order in the list.
    public void insert(HurricaneRowData newValue)
    {
    }

    //Return the entire list as a multi-line String
    public String toString()
    {
        return "";  //PLACEHOLDER
    }

    // Post: Returns true if this linked list contains the given value
    public boolean contains(HurricaneRowData value)
    {
        return true;    //PLACEHOLDER
    }

    // Pre: This linked list contains the given value
    // Post: Returns the LinkedList element whose value matches the given value
    public DoublyLinkedSortedList getByValue(HurricaneRowData value)
    {
    }
}