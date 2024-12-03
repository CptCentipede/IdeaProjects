/*
Name: Miles Aether
Date: 1 December 2024
Class: CSCI 2251
Purpose: Client with GUI that send data to server from JTextField
*/

//imports for client components
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private String userInput = "";
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;

    //instance variables for GUI components
    private JTextField enterField;  // JTextField to enter the matrix file name
    private JTextArea displayArea;  // JTextAreato display the computation result
    private File file;
    private Scanner fileInput;

    //Use local host and port 12345
    private final String host = "127.0.0.1";
    private final int portNumber = 12345;


    //constructor with GUI
    public Client()
    {
        //name of GUI
        super("Client");

        // create enterField and register its listener
        enterField = new JTextField("Enter file name here");
        enterField.setEditable(false);
        add(enterField, BorderLayout.NORTH);

        displayArea = new JTextArea();
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        enterField.addActionListener(
                new ActionListener() {
                    // get the file name specified by user
                    public void actionPerformed(ActionEvent event) {
                        getFile(event.getActionCommand());
                        //sends the text to server
                        Object toSend = event.getActionCommand();
                        sendData(toSend);
                        //resets text field after pressing enter
                        enterField.setText("");
                    }
                }
        );

        // Terminate connection properly when user closes the GUI. https://stackoverflow.com/questions/16372241/run-function-on-jframe-close
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Terminate the connection when the window gets closed.
                sendData("TERMINATE");
            }
        });

        //set size and visibility
        setSize(400, 300); // set size of window
        setVisible(true);  // show window
    }   //end constructor

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
        //instantiate object
        Object message;
        do
        {
            //set message equal to something, so that it doesn't disconnect
            message = "";

            //if the message isn't empty send to server
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
    }   //end processConnection

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
    private void sendData(Object message)
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
    private void getFile(Object file_name)
    {
        //set file to the string value of the name of the file input
        file = new File(String.valueOf(file_name));
        try {
            //create scanner for file
            fileInput = new Scanner(file);

            //print file name input to command line
            System.out.print(file + "\n");

            //while the file has more lines
            while (fileInput.hasNextLine()) {
                //read the dimensions for the number of rows and columns
                int numRows = fileInput.nextInt();
                int numCols = fileInput.nextInt();

                //create two matrices and store data from file using matrixFromFile method
                int[][] matrix1 = matrixFromFile(numRows, numCols, fileInput);
                int[][] matrix2 = matrixFromFile(numRows, numCols, fileInput);

                //send these matrices to the server
                sendData(matrix1);
                sendData(matrix2);
            }


        }   //end try
        catch (FileNotFoundException e) {
            //print out whatever else might have been typed to command line
            System.out.print(file + "\n");
            //if what was type was TERMINATE close the GUI
            if(file_name.equals("TERMINATE"))
            {
                System.exit(1);
            }
        }


    }   //end getFile

    //method that reads the matrix from the file and sets it to the new matrix to be used in the thread
    public static int[][] matrixFromFile(int rows, int columns, Scanner file_reader)
    {
        //create a matrix to be returned
        int[][] tempMatrix = new int[rows][columns];

        //populate the matrix
        //first the rows
        for(int i=0; i<rows; i++)
        {
            //then the columns
            for(int j=0; j<columns; j++)
            {
                //put the next integer at index [i][j]
                tempMatrix[i][j] = file_reader.nextInt();
            }   //end for loop cols
        }   //end for loop rows

        //return the matrix
        return tempMatrix;
    }  //end matrixFromFile

    // manipulates enterField in the event-dispatch thread
    private void setTextFieldEditable(final boolean editable)
    {
        SwingUtilities.invokeLater(
                new Runnable()
                {
                    public void run() // sets enterField's editability
                    {
                        enterField.setEditable(editable);
                    }
                }
        );
    }
}   //end class