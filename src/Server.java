import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private ServerSocket server;
    private Socket connection;

    private final int portNumber = 12345;
    private final int backlogLimit = 100;

    // set up and run server
    public void runServer()
    {
        try // set up server to receive connections; process connections
        {
            //backlog is how many clients can wait in the queue
            server = new ServerSocket(portNumber, backlogLimit);

            while (true)
            {
                try
                {
                    waitForConnection();
                    getStreams();
                    processConnection();
                }
                catch (EOFException e)
                {
                    System.out.println("\nServer terminated connection");
                }
                finally
                {
                    closeConnection();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // wait for connection to arrive, then display connection info
    private void waitForConnection() throws IOException
    {
        System.out.println("Waiting for connection\n");

        connection = server.accept();

        System.out.println("New connection received from: " + connection.getInetAddress().getHostName());
    }

    // get streams to send and receive data
    private void getStreams() throws IOException
    {
        output = new ObjectOutputStream(connection.getOutputStream());
        output.flush();

        input = new ObjectInputStream(connection.getInputStream());
    }

    // process connection with client
    private void processConnection() throws IOException
    {
        Object message = null;

        do // process messages sent from client
        {
            try // read message and display it
            {
                message = input.readObject();

                if(message instanceof String)
                {
                    String data_from_client = (String)message;
                    System.out.println("\nCLIENT>>>" + message);
                    System.out.println();
                }
                if(message instanceof int[][])
                {
                    int[][] data_from_client = (int[][])message;

                    for(int i=0; i<data_from_client.length; i++)
                    {
                        for(int j=0; j<data_from_client[i].length; j++)
                        {
                            System.out.print(data_from_client[i][j] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
            }
            catch (ClassNotFoundException e)
            {
                System.out.println("\nUnknown object type received");
            }

            //Unless client requests terminate, reply.
            if(!(message instanceof String && ((String)message).equals("TERMINATE") ))
            {
				/* Send the same message back to 
				the client every time. This
				prevents the Client from hanging
				on this line of code:
				message = (String) input.readObject(); */
                sendData("OK, Client\n");
            }
        } while (!(message instanceof String && ((String)message).equals("TERMINATE") ));
		
		/* Send this so the client has
		confirmation that the connection 
		should be terminated. */
        sendData("TERMINATE");
    }

    // close streams and socket
    private void closeConnection()
    {
        System.out.println("\nTerminating connection\n");
        try
        {
            output.close(); // close output stream
            input.close(); // close input stream
            connection.close(); // close socket
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // send message to client
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
}