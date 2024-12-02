import javax.swing.JFrame;

public class ClientStart
{
    public static void main(String[] args)
    {
        //create the client and set its default to close after pressing the x
        Client c = new Client();
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.runClient();
    }
}