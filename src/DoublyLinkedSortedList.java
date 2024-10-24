public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface
{
    //instance variables
    private HurricaneRowData value;
    private DoublyLinkedSortedList next = null;
    private DoublyLinkedSortedList previous = null;

    //constructors
    public DoublyLinkedSortedList()
    {
    }

    public DoublyLinkedSortedList(HurricaneRowData v)
    {
        this.value = v;
    }

    //Get the value of the current DoublyLinkedSortedList
    @Override
    public HurricaneRowData getValue() {
        return value;
    }

    //Return true if next is not null
    public boolean hasNext()
    {
        //if the next one is null return true
        if (next == null)
        {
            return true;
        }   //end if

        //otherwise return false
        return false;
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
        //if the previous is null return true
        if(previous == null)
        {
            return true;
        }

        //otherwise return false
        return false;
    }

    //Set previous to be the given DoublyLinkedSortedList
    public void setPrevious(DoublyLinkedSortedList previous)
    {
        this.previous = previous;
        previous.next = this;
    }

    //Return a reference to the previous DoublyLinkedSortedList
    public DoublyLinkedSortedList getPrevious()
    {
        return previous;
    }

    //Return a reference to the first DoublyLinkedSortedList element in the list
    public DoublyLinkedSortedList getFirst()
    {
        //if the previous is null return this
        if (previous == null)
        {
            return this;
        }
        //otherwise return the other previous value
        else
        {
            return previous.getFirst();
        }
    }

    //Return a reference to the last DoublyLinkedSortedList element in the list
    @Override
    public DoublyLinkedSortedList getLast() {
        if(next == null) {
            return this;
        }
        else{
            return next.getLast();
        }
    }

    //Remove the DoublyLinkedSortedList element that has toRemove as its value
    @Override
    public DoublyLinkedSortedList remove(HurricaneRowData toRemove) {
        previous.setNext(next);
        return this;
    }

    public int getValueAce(HurricaneRowData data)
    {
        return data.getAce();
    }

    //Insert a new DoublyLinkedSortedList element that has the given newValue in order in the list.
    @Override
    public void insert(HurricaneRowData data)
    {
        //if the value is null then set it equal to the value and return
        if (value == null)
        {
            value = data;
            return;
        }   //end value is null

        //else the value is not null then figure out where the new piece of data goes
        else {
            //create a variable for the current value that will be the data from HurricaneRowData
            DoublyLinkedSortedList currValue = new DoublyLinkedSortedList(data);

            //if the value's ace is greater than the ace of the current value
            if (value.getAce() > data.getAce()) {
                //and if the next value is null
                if(hasNext())
                {
                    setNext(currValue);
                }
            }

            else
            {
                if(hasPrevious())
                {
                    setPrevious(currValue);
                }

            }
        }   //end else
    }   //end insert

    @Override
    //Return the entire list as a multi-line String
    public String toString()
    {
        return String.format("%10d %10d %10d %10d %10d", value.getYear(), value.getAce(), value.getStormCount(),
                value.getHurrCount(), value.getMajHurrCount());
    }

    // Post: Returns true if this linked list contains the given value
    public boolean contains(HurricaneRowData value)
    {
        //get the first value from the list
        DoublyLinkedSortedList current = this.getFirst();

        //if the value of that object is equal to the value you're looking for return true
        if(current.getValue() == value)
            return true;

        //while the current object has more data and is equal to the value return true
        while(current.hasNext())
        {
            current = current.getNext();
            if(current.getValue() == value)
                return true;
        }

        //otherwise return false
        return false;
    }

    // Pre: This linked list contains the given value
    // Post: Returns the LinkedList element whose value matches the given value
    public DoublyLinkedSortedList getByValue(HurricaneRowData value)
    {
        return null;    //PLACEHOLDER
    }
}
