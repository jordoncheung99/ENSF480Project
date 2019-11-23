package Server;/*
 * A simple TCP client that sends messages to a server and display the message
   from the server. 
 * For use in CPSC 441 lectures
 * Instructor: Prof. Mea Wang
 */


import java.io.*; 
import java.net.*; 

class TCPClient2 { 

    public static void main(String args[]) throws Exception 
    { 
        if (args.length != 2)
        {
            System.out.println("Usage: TCPClient <Server.Server IP> <Server.Server Port>");
            System.exit(1);
        }

        // Initialize a client socket connection to the server
        Socket clientSocket = new Socket(args[0], Integer.parseInt(args[1])); 

        // Initialize input and an output stream for the connection(s)
		PrintWriter outBuffer =
			new PrintWriter(clientSocket.getOutputStream(), true);
		
        BufferedReader inBuffer = 
          new BufferedReader(new
          InputStreamReader(clientSocket.getInputStream())); 

        // Initialize user input stream
        String line; 
        BufferedReader inFromUser = 
        new BufferedReader(new InputStreamReader(System.in)); 

        // Get user input and send to the server
        // Display the echo meesage from the server
        System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
        line = inFromUser.readLine(); 
        while (!line.equals("logout"))
        {
            // Send to the server
			outBuffer.println(line);
            		
			
            //Dose it have to save a file?
            String[] parts= line.split(" ");
	   		 //Error checking
	   		 if(parts.length == 2 && parts[0].equals("get")) {
	   			 //Save the file
	   			 //process file name
	
	   			 String name = parts[1];
	   			 String extention;
	   			 if(name.indexOf('.') > 0) {
		   			 extention = name.substring(name.indexOf('.'), name.length()); 
		   			 name = name.substring(0, name.indexOf('.'));
		   			 name += "-" + clientSocket.getLocalPort() + extention;
		   			 saveFile(inBuffer, name);
	   			 }
	   		 }else{
				 while(!inBuffer.ready()){
					Thread.sleep(50);
				 }
				 Thread.sleep(100);
				// Getting response from the server
				while(inBuffer.ready()){
					line = inBuffer.readLine();
					System.out.println("Server.Server: " + line);
				}
			}
            System.out.print("Please enter a message to be sent to the server ('logout' to terminate): ");
            line = inFromUser.readLine(); 
            
        }

        // Close the socket
        clientSocket.close();           
    } 
    
    private static void saveFile(BufferedReader inReader, String Filename)throws InterruptedException {
    	try {
            String line = "";
            //Error check the first line
            if(inReader.ready()) {
            	line = inReader.readLine();
            	String[] check = line.split(" ");
            	if(check[0].equals("Unknown")) {
            		//Failed
            		return;
            	}
            }
			while(!inReader.ready()){
				Thread.sleep(100);
			}
			Thread.sleep(100);
			File file = new File(Filename);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(line);
			while(inReader.ready()) {
            	line = inReader.readLine();
            	out.write(line);
            }
			out.close();
			System.out.println("File saved in" + Filename + " (" + file.length() + " bytes) ");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
} 
