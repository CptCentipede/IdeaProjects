public class SortedTreeSet implements SortedTreeSetInterface
{
    //create instance variables
    private SortedTreeSet parent; //root
    private SortedTreeSet leftChild; //left branch
    private SortedTreeSet rightChild; //right branch
    private boolean hasValue;
    private Person value;

    //create an empty tree
    public SortedTreeSet()
    {
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
        this.hasValue = false;
    }   //end constructor

    //method that gets the data for the person being added
    public Person getPerson() {return value;}

    //leftChild hasLeft, setLeft, and getLeft
    public boolean hasLeft()
    {
        //if the leftChild is null return false
        if (leftChild == null)
        {
            return false;
        }   //end if
        //otherwise return true
        return true;
    }   //end hasLeft
    public void setLeft(SortedTreeSet leftChild)
    {
        this.leftChild = leftChild;
    }   //end setLeft
    public SortedTreeSet getLeft()
    {
        return this.leftChild;
    }   //end getLeft

    //rightChild hasRight, setRight, getRight
    public boolean hasRight()
    {
        //if the rightChild is null return false
        if (rightChild == null)
        {
            return false;
        }   //end if
        //otherwise return true
        return true;
    }   //end hasRight
    public void setRight(SortedTreeSet rightChild)
    {
        this.rightChild = rightChild;
    }   //end setRight
    public SortedTreeSet getRight()
    {
        return this.rightChild;
    }   //end getRight

    //method that adds the person to the tree
    public void add(Person p)
    {
        //If the current node has no value, make the current value equal value and set has_value to true.
        if(!hasValue)
        {
            this.value = p;
            this.hasValue = true;
        }
        else
        {
            //if value is less than the current node's value, add the value to the left child.
            if (!contains(value) && value.getName().compareTo(this.value.getName()) < 0) {
                //If the child does not exist (child == null), create it.
                if (leftChild == null) {
                    //System.out.println("Left child null so creating."); //TESTING
                    leftChild = new SortedTreeSet();
                    leftChild.parent = this;
                }
                leftChild.add(value);
            }

            //If the value is greater than or equal to the current node's value, add the value to the right child.
            if (!contains(value) && value.getName().compareTo(this.value.getName()) > 0) {
                //System.out.println(value+" >= "+this.value); //TESTING
                //If the child does not exist (child == null), create it.
                if (rightChild == null) {
                    //System.out.println("Right child null so creating."); //TESTING
                    rightChild = new SortedTreeSet();
                    rightChild.parent = this;
                }
                rightChild.add(value);
            }
        }
    }

    //checks if tree already contains data
    public boolean contains(Person value)
    {
        //If the current node has no value, return false.
        if(!hasValue)
        {
            return false;
        }
        //Does this have the value
        else if(this.value == value)
        {
            return true;
        }
        //Otherwise, if value is less than the current node's value, check the tree to the left.
        else if(value.getName().compareTo(this.value.getName()) < 0)
        {
            if(leftChild == null)
                return false;
            return leftChild.contains(value);
        }
        //If the value is greater than or equal to the current node's value, check the tree to the right.
        else
        {
            if(rightChild == null)
                return false;
            return rightChild.contains(value);
        }
    }

    @Override
    public String toString()
    {
        String toReturn = "";
        if(leftChild != null)
        {
            toReturn += leftChild.toString();
        }
        //Get this Tree's value
        toReturn += value+", ";
        //Get the right subtree String
        if(rightChild != null)
        {
            toReturn += rightChild.toString();
        }
        return toReturn;  //PLACEHOLDER
    }
}
