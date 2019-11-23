package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public abstract class Handler {
    //At the end this task must end with sending a string to the client for flow to work

    protected PrintWriter socketOut;
    protected BufferedReader socketIn;
    public Handler(BufferedReader socketIn,PrintWriter socketOut){
        this.socketOut = socketOut;
        this.socketIn = socketIn;
    }
    /**
     *
     * @param input
     * @return if the task was done sucessfully
     */
    public abstract boolean doTask(String input);



    public static String[] processString(String input){
        return input.split("#");
    }

    protected void sendString(String message){
        socketOut.println(message);
        socketOut.flush();
    }
}
