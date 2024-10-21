public class DoublyLinkedSortedList implements DoublyLinkedSortedListInterface
{
    //instance variables
    private HurricaneRowData value = null;
    private DoublyLinkedSortedList next = null;
    private DoublyLinkedSortedList previous = null;

    public DoublyLinkedSortedList()
    {

    }

    public DoublyLinkedSortedList(HurricaneRowData v)
    {
        this.value = v;
    }

    //Get the value of the current DoublyLinkedSortedList
    public HurricaneRowData getValue(DoublyLinkedSortedList data)
    {
        return value;
    }

    //Return true if next is not null
    public boolean hasNext()
    {
        if (next == null)
        {
            return false;
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
        if(previous == null)
        {
            return true;    //PLACEHOLDER
        }

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
        if (previous == null)
        {
            return this;
        }
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

    //Insert a new DoublyLinkedSortedList element that has the given newValue in order in the list.
    @Override
    public HurricaneRowData insert(HurricaneRowData newValue)
    {
        if (newValue == null)
        {
            return newValue;
        }

        // Sorted part of the list
        HurricaneRowData sorted = null;

        // Current node to be inserted
        HurricaneRowData currentNode = newValue;

        // Traverse the list to sort each element
        while (currentNode != null) {

            // Store the next node to process
            HurricaneRowData next = currentNode.next;

            // Insert `curr` into the sorted part
            if (sorted == null ||
                    sorted.value >= currentNode.value) {
                currentNode.next = sorted;

                // If sorted is not empty, set its `prev`
                if (sorted != null) sorted.previous = currentNode;

                // Update sorted to the new head
                sorted = currentNode;
                sorted.previous = null;

            }
            else {

                // Pointer to traverse the sorted part
                HurricaneRowData currentSorted = sorted;

                // Find the correct position to insert
                while (currentSorted.next != null &&
                        currentSorted.next.value < currentNode.value) {
                    currentSorted = currentSorted.next;
                }

                // Insert `curr` after `currentSorted`
                currentNode.next = currentSorted.next;

                // Set `prev` if `curr` is not inserted
                // at the end
                if (currentSorted.next != null)
                    currentSorted.next.prev = currentNode;

                // Set `next` of `currentSorted` to `curr`
                currentSorted.next = currentNode;
                currentNode.previous = currentSorted;
            }
            currentNode = next;
        }

        return sorted;
    }

    @Override
    //Return the entire list as a multi-line String
    public String toString()
    {
        return " ";  //PLACEHOLDER
    }

    // Post: Returns true if this linked list contains the given value
    public boolean contains(HurricaneRowData value)
    {
        DoublyLinkedSortedList current = this.getFirst();

        if(current.getValue() == value)
            return true;

        while(current.hasNext())
        {
            current = current.getNext();
            if(current.getValue() == value)
                return true;
        }

        return false;
    }

    // Pre: This linked list contains the given value
    // Post: Returns the LinkedList element whose value matches the given value
    public DoublyLinkedSortedList getByValue(HurricaneRowData value)
    {
        return null;    //PLACEHOLDER
    }
}
