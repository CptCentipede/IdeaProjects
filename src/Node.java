public class Node<HurricaneRowData> {
    private HurricaneRowData value;
    private Node next;
    private Node previous;

    //set the values for the Nodes
    public Node(HurricaneRowData value)
    {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    //getters
    public HurricaneRowData getData() {return value;}
    public Node<HurricaneRowData> getNext() {return next;}

    //setter
    public void setNext(Node<HurricaneRowData> n) {next = n;}

    //see if there is a Node that is next
    public boolean hasNext() {return next!= null;}
}
