public class Node<HurricaneRowData> {
    private HurricaneRowData value;
    private Node next;
    private Node previous;

    public Node(HurricaneRowData value)
    {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public HurricaneRowData getData() {return value;}
    public Node<HurricaneRowData> getNext() {return next;}
    public void setNext(Node<HurricaneRowData> n) {next = n;}
    public boolean hasNext() {return next!= null;}
}
