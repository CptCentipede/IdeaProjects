//imports for client components
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

//imports for GUI components
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame
{
    //instance variables for client components
    //private final Scanner userInput =
            //new Scanner(System.in);
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;

    //instance variables for GUI components
    private JTextField enterField;  // JTextField to enter the matrix file name
    private JTextArea displayArea;  // JTextAreato display the computation result
    private File file;
    private Scanner userInput;

    //Use local host and port 12345
    private final String host = "127.0.0.1";
    private final int portNumber = 12345;


    //set up GUI
    public Client()
    {
        super("Client");

        // create enterField and register its listener
        enterField = new JTextField("Enter file name here");
        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        enterField.addActionListener(
                new ActionListener() {
                    // get the file name specified by user
                    public void actionPerformed(ActionEvent event) {
                        getFile(event.getActionCommand());
                    }
                }
        );

        setSize(400, 300); // set size of window
        setVisible(true);  // show window
    }

    // connect to server and process messages from server
    public void runClient()
    {
        try
        {
            connectToServer();
            getStreams();
            processConnection();
        }
        catch (EOFException e)
        {
            System.out.println("\nClient terminated connection");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            closeConnection();
        }
    }

    // connect to server
    private void connectToServer() throws IOException
    {
        System.out.println("Attempting connection\n");

        // create Socket to make connection to server
        client = new Socket(InetAddress.getByName(host), portNumber);

        // display connection information
        System.out.println("Connected to: " + client.getInetAddress().getHostName());
    }

    // get streams to send and receive data
    private void getStreams() throws IOException
    {
        // set up output stream for objects
        output = new ObjectOutputStream(client.getOutputStream());

        output.flush(); // flush output buffer to send header information

        // set up input stream for objects
        input = new ObjectInputStream(client.getInputStream());

        System.out.println("\nGot I/O streams\n");
    }

    // process connection with server
    private void processConnection() throws IOException
    {
        String message;
        do
        {
            message = userInput.nextLine();
            if(!message.equals("")) {
                sendData(message);
            }
            try // read message and display it
            {
                message = (String)input.readObject();
                System.out.println("\nSERVER>>>" + message);
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("\nUnknown object type received");
            }

        } while (!message.equals("TERMINATE"));
    }

    // close streams and socket
    private void closeConnection()
    {
        System.out.println("\nClosing connection");
        try
        {
            output.close(); // close output stream
            input.close(); // close input stream
            client.close(); // close socket
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // send message to server
    private void sendData(String message)
    {
        try
        {
            output.writeObject(message);
            output.flush();
        }
        catch (IOException e)
        {
            System.out.println("\nError writing object");
        }
    }

    // load document
    private void getFile(String file_name)
    {
        file = new File(file_name);
        try {
            userInput = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.printf("%nError on file: %s (either enpty or wrong file format)%n%n", file);
            e.printStackTrace();
            System.exit(1);
        }

        String s;
        while (userInput.hasNextLine()) {
            s = userInput.nextLine();
            displayArea.append(s + "\n");
        }
    }
}