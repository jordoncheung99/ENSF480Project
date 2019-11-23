package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class AddHandler extends Handler {

    public AddHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    public  static void main(String args[]){
        AddHandler a = new AddHandler(null,null);
    }

    @Override
    public boolean doTask(String input) {
        if (!input.contains("ADD")){
            return false;
        }
        String parts[] = input.split("#");
        Property p = new Property(parts[1]);

        return true;
    }
}
