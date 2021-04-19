
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.awt.*; 
import javax.swing.*;

/**
 * How to run this application?
 * 1. Open Terminal
 * 2. Change directory to workspace-dad/udplab/bin
 * 3. Type java exe1.upd.receiver.ServerApplication
 * 
 * @author Radwan
 *
 */
public class ServerApplication {

	public static void main(String[] args) {
		
		
		// Server UDP socket runs at this port
		final int serverPort=50002;
		
		try {

			String WindowMsg = "[+] Starting server [+]";
			JFrame frame = new JFrame("Server application");//Window
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Set how to close
			JLabel textLabel = new JLabel("<html>"+WindowMsg+"</html>",SwingConstants.CENTER);  //Window information as label     
			textLabel.setPreferredSize(new Dimension(300, 100));//Set the label size
			frame.getContentPane().add(textLabel, BorderLayout.CENTER);//Display the window.       
			frame.setLocationRelativeTo(null);//how to place the elements within the window
			frame.pack();//Prepare all graphics elements together
			frame.setVisible(true);//Show the window

			// Instantiate a new DatagramSocket to receive responses from the client
		    DatagramSocket serverSocket = new DatagramSocket(serverPort);
		    
		    // Create buffers to hold receiving data.
		    byte receivingDataBuffer[] = new byte[1024];
		    
		    // Instantiate a UDP packet to store the client data using the buffer for receiving data
		    DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);

			//Showing in window information
			WindowMsg = WindowMsg + "<br/>[+] Waiting for client to connect [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		    
		    // Receive data from the client and store in inputPacket
		    serverSocket.receive(inputPacket);
		    
		    // Printing out the client sent data
		    String receivedData = new String(inputPacket.getData());

			//Showing in window information
			WindowMsg = WindowMsg + "<br/>[+] Received: " + receivedData +"[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		    
			//Trims the received data with spaces modified with \\s instead of space
			String trim = receivedData.trim();
			int receivedDataSize = 0;

			//Validates the string trimmed
			if(trim.isEmpty()){
				textLabel.setText("<html>" +WindowMsg + "<br/>[x] Empty data received [x]"+ "</html>");
			}
			else{
			//counts the words within the string
				receivedDataSize = trim.split("\\s+").length;
			}

		    // Process data - convert to upper case
		    String sendingData = "String size: " + String.valueOf(receivedDataSize);
		    
		    // Creating corresponding buffer to send data
		    byte sendingDataBuffer[] = sendingData.getBytes();
		    
		    // Get client's address
		    InetAddress senderAddress = inputPacket.getAddress();
		    int senderPort = inputPacket.getPort();
		    
		    // Create new UDP packet with data to send to the client
		    DatagramPacket outputPacket = new DatagramPacket(sendingDataBuffer, 
		    		sendingDataBuffer.length, senderAddress,senderPort);
		    
		    // Send the created packet to client
		    serverSocket.send(outputPacket);

			//Showing in window information
		    WindowMsg = WindowMsg + "<br/>[+] Sent to the client: " + sendingData + "[+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");

		    // Close the socket connection
		    serverSocket.close();

			//Showing in window information
		    WindowMsg = WindowMsg + "<br/>[+] Server closed [+]";
		    textLabel.setText("<html>" +WindowMsg+ "</html>");
		      
		} catch (Exception ex) {
			
			System.out.println("Durian Tunggal... we got problem");
			ex.printStackTrace();
		}

	}

}
