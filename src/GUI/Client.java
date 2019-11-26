package GUI;/*
 * A simple TCP client that sends messages to a server and display the message
   from the server. 
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */


import Server.Manager;

import java.io.*;
import java.net.Socket;

class Client {
	Socket clientSocket;
	PrintWriter outBuffer;
	BufferedReader inBuffer;
	BufferedReader inFromUser;

	Client(String IP, int port){
		try {
			clientSocket = new Socket(IP,port);
			// Initialize input and an output stream for the connection(s)
			outBuffer = new PrintWriter(clientSocket.getOutputStream(), true);
			inBuffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			// Initialize user input stream
			inFromUser = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try{
			runClient();
		}catch (IOException e){

		}catch (InterruptedException e){

		}
	}


	public void runClient() throws IOException, InterruptedException {
		String line = "";
		String serverOut = "";
		while (!line.equals("logout")) {
			// Send to the server

			serverOut = readServer();
			System.out.println(serverOut);

			if(serverOut.contains("logged in")){
				break;
			}

			line = inFromUser.readLine();
			outBuffer.println(line);



			//System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
		}
		openCorrectGUI(serverOut);


		// Close the socket
		//clientSocket.close();
	}

	private void openCorrectGUI(String input){
		if(input.contains("LANDLORD")){
			LandlordGUI gui = new LandlordGUI(outBuffer,inBuffer);
		}else if(input.contains("MANAGER")){
			ManagerGUI gui = new ManagerGUI(outBuffer, inBuffer);
		}else if (input.contains("RENTER")){
			System.out.println("REGRENTER");
		}else if (input.contains("RENT")){
			System.out.println("Normie");
		}
	}

    public static void main(String args[]) throws Exception 
    {
      Client c = new Client("LocalHost",4099);
    }

    public String readServer() throws IOException, InterruptedException {
    	String sendBack = "";
		while(!inBuffer.ready()){
			Thread.sleep(50);
		}
		Thread.sleep(100);
		// Getting response from the server
		while(inBuffer.ready()){
			sendBack += inBuffer.readLine() + "\n" ;
		}
		return sendBack;
	}

	public static String readServer(BufferedReader inBuffer) throws IOException, InterruptedException {
		String sendBack = "";
		while(!inBuffer.ready()){
			Thread.sleep(50);
		}
		Thread.sleep(100);
		// Getting response from the server
		while(inBuffer.ready()){
			sendBack += inBuffer.readLine() + "\n" ;
		}
		return sendBack;
	}
} 
