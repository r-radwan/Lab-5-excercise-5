import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.*; 
import javax.swing.*;


/**
 * This program demonstrate the implementation of DatagramPacket and DatagramSocket
 * for UDP.  
 * 
 * This program represents a client-side application that sends  a text data from the client.
 * 
 * @author Radwan
 *
 */
public class ClientApplication {

	public static void main(String[] args) {
		
		// The server port to which the client socket is going to connect
		final int SERVERPORT = 50002;

		int bufferSize = 1024;

		try {
			String WindowMsg = "[+] Starting Client [+]";
			JFrame frame = new JFrame("Client application");//Window
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set how to close
			JLabel textLabel = new JLabel("<html>"+ WindowMsg +"</html>",SwingConstants.CENTER);//Window information as label
			textLabel.setPreferredSize(new Dimension(300, 100)); //Set the label size
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);//Display the window.       
			frame.setLocationRelativeTo(null); //how to place the elements within the window
			frame.pack();//Prepare all graphics elements together
			frame.setVisible(true);//Show the window
			
			// Instantiate client socket
			DatagramSocket clientSocket = new DatagramSocket();

			// Get the IP address of the server
			InetAddress serverAddress = InetAddress.getByName("localhost");

			// Create buffer to send data
			byte sendingDataBuffer[] = new byte[bufferSize];

			// Convert data to bytes and store data in the buffer
			String sentence = "An attempt from UDP client";
			sendingDataBuffer = sentence.getBytes();

			// Creating a UDP packet 
			DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer,
					sendingDataBuffer.length, serverAddress, SERVERPORT);

			//Showing in window information
			WindowMsg = WindowMsg + "<br/>[+] Sending data to server [+]";
			textLabel.setText("<html>" +WindowMsg+ "</html>");

			// Sending UDP packet to the server
			clientSocket.send(sendingPacket);
			
			//Showing in window information
			WindowMsg = WindowMsg + "<br/> [!] Sented: " + sentence + "[!]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");

			// Create buffer to receive data
			byte receivingDataBuffer [] = new byte[bufferSize];
			
			// Receive data packet from server
		    DatagramPacket receivingPacket = new DatagramPacket(receivingDataBuffer,
		    		receivingDataBuffer.length);
		    clientSocket.receive(receivingPacket);
		    
		    // Unpack packet
		    String dataSize = new String(receivingPacket.getData());

			//Showing in window information
			WindowMsg = WindowMsg + "<br/>[+] From server: " + dataSize + "[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
			// Closing the socket connection with the server
			clientSocket.close();
			
			WindowMsg = WindowMsg + "<br/>[+] Client closed [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		} catch (Exception ex) {

			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
