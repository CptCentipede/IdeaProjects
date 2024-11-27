import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server
{
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	private final int portNumber = 12345;
	private final int backlogLimit = 100;

//	ArrayList<Integer> arrListToAvg;

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
		ArrayList<Integer> arrListToAvg = new ArrayList<>();


		do // process messages sent from client
		{
			try // read message and display it
			{
				message = input.readObject();

				if(message instanceof String)
				{
					System.out.println("\nCLIENT>>>" + message);

					if(!message.equals("TERMINATE"))
					{
						sendData("Server Received String " + message);
					}
				}
				else if(message instanceof Integer)
				{
					arrListToAvg.add((Integer) message);
					System.out.println("\nCLIENT>>>" + avgArrList(arrListToAvg));

					sendData("Server Received int " + message);
				}
				else
				{
					//Maybe put an error check here.
					System.out.println("Type not recognized.");
					System.out.println(message);
				}
			} 
			catch (ClassNotFoundException e) 
			{
				System.out.println("\nUnknown object type received");
			}
			

		} while (!(message instanceof String &&((String)message).equals("TERMINATE")));
		
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

	//method that averages the values of the growing array list
	private int avgArrList(ArrayList<Integer> arrListToAvg)
	{
		int avg = 0;
		for(int i=0; i<arrListToAvg.size(); i++)
		{
			avg += arrListToAvg.get(i);
		}

		avg = avg/arrListToAvg.size();

		return avg;
	}
}