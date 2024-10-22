public class ThreadDemo extends Thread
{
    private int start;
    private String identifier;

    public ThreadDemo(int start, String identifier)
    {
        this.start = start;
        this.identifier = identifier;
    }

    @Override
    public void run()
    {
        for(int i=start; i<10000; i++)
            System.out.println(identifier+": "+i);
    }
}