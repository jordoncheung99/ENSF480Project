package GUI;/*
 * A simple TCP client that sends messages to a server and display the message
   from the server. 
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */


import java.io.*;
import java.net.Socket;

class Client {

    public static void main(String args[]) throws Exception 
    { 
        if (args.length != 2)
        {
            System.out.println("Usage: TCPClient <Server.Server IP> <Server.Server Port>");
            System.exit(1);
        }


        //Assume local host and 4099
		Socket clientSocket = new Socket("localHost",4099);

        // Initialize input and an output stream for the connection(s)
		PrintWriter outBuffer = new PrintWriter(clientSocket.getOutputStream(), true);
		
        BufferedReader inBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Initialize user input stream
        String line; 
        BufferedReader inFromUser = 
        new BufferedReader(new InputStreamReader(System.in)); 

        //Wait for input message
		while(!inBuffer.ready()){
			Thread.sleep(50);
		}
		Thread.sleep(100);
		while(inBuffer.ready()){
			line = inBuffer.readLine();
			System.out.println("Server: " + line);
		}

        System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
        line = inFromUser.readLine(); 
        while (!line.equals("logout")) {
			// Send to the server
			outBuffer.println(line);

			while(!inBuffer.ready()){
				Thread.sleep(50);
			}
			Thread.sleep(100);
			// Getting response from the server
			while(inBuffer.ready()){
				line = inBuffer.readLine();
				System.out.println("Server.Server: " + line);
			}

			System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
			line = inFromUser.readLine();

        }

        // Close the socket
        clientSocket.close();           
    }
} 
