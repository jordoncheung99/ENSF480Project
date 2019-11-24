package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.System.exit;
import static java.lang.System.in;

public class TestCommunicator implements Runnable{
    protected Socket aSocket;
    protected BufferedReader socketIn;
    protected PrintWriter socketOut;
    protected User client;
    TestCommunicator(Socket client){
        aSocket = client;
        try {
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
            socketOut = new PrintWriter(aSocket.getOutputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void sendString(String message) {
        socketOut.println(message);
        socketOut.flush();
    }


    @Override
    public void run() {
        try{
            initalizeUser();
        }catch (IOException e){
            System.out.println("Error detected while getting user, exiting");
            exit(1);
        }
        try {
            handleRPMSRouting();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }




    private void initalizeUser() throws IOException{
        sendString("Please enter 'rent' to start looking at properties");
        sendString("OR 'login' to login into an account");
        sendString("OR 'register' to create a new account");
        while(true) {
            String input = socketIn.readLine();
            if (input.equalsIgnoreCase("rent")) {
                client = null;
                return;
            }
            if (input.equalsIgnoreCase("login")) {
                login();
                return;
            }
            if(input.equalsIgnoreCase("register")){
                register();
                return;
            }
            sendString("That's not a valid option!");
        }
    }

    private void register() throws  IOException{
        LoginServer loginServer = LoginServer.getInstance();
        while (true){
            sendString("What is your username?");
            String username = socketIn.readLine();
            if(!loginServer.exists(username)){
                while(true){
                    sendString("What is your password?");
                    String password = socketIn.readLine();
                    while(true){
                        String accType = null;
                        sendString("Are you an renter? landlord? or manager?");
                        String type = socketIn.readLine();
                        if (type.equalsIgnoreCase("renter")){
                            accType = "RENTER";
                        }else if(type.equalsIgnoreCase("landlord")){
                            accType = "LANDLORD";
                        }else if(type.equalsIgnoreCase("manager")){
                            accType = "MANAGER";
                        }
                        sendString("What is your first name?");
                        String fname = socketIn.readLine();
                        sendString("What is your last name?");
                        String lname = socketIn.readLine();
                        Name name = new Name(fname, lname);
                        sendString("Country?");
                        String country = socketIn.readLine();
                        sendString("Province?");
                        String province = socketIn.readLine();
                        sendString("City?");
                        String city = socketIn.readLine();
                        sendString("Street?");
                        String street = socketIn.readLine();
                        sendString("Postal Code?");
                        String postal = socketIn.readLine();
                        Address address = new Address(street, city, province, country, postal);
                        if (accType != null){
                            loginServer.addUser(username,password, accType, name, address);
                            client = loginServer.validate(username,password);
                            return;
                        }
                        sendString("Not a valid type");
                    }
                }
            }
            sendString("That's not a valid username, it already exists!");
        }
    }

    private void login() throws  IOException{
        LoginServer loginServer = LoginServer.getInstance();
        while(true){
            sendString("Please enter you username");
            String username = socketIn.readLine();
            sendString("Please enter you password");
            String password = socketIn.readLine();
            System.out.println("Username: " +username + " Length: " + username.length());
            System.out.println("Password: " +password + " Length: " + password.length());
            User temp = loginServer.validate(username,password);
            if (temp != null){
                client =  temp;
                return;
            }else{
                sendString("That's not a valid username / password");
            }
        }
    }


    private void handleRPMSRouting() throws IOException{
        sendString("User has successfully logged in as a " + client.getType());
        switch(client.getType()) {
            case "RENTER":
                regRentHandler();
                break;
            case "LANDLORD":
                landLordHandler();
                break;
            case "MANAGER":
                managerHandler();
                break;
        }
    }

    private void renterHandler(){
        while(true){

        }
    }

    private void regRentHandler(){
        while(true){
            Renter user = new Renter();
        }
    }

    private void landLordHandler(){
        while(true){
            LandLord user = new LandLord();
        }
    }

    private void managerHandler(){
        while(true){
            Manager user = new Manager();
        }
    }

    private void filterSearch(){

    }


}
