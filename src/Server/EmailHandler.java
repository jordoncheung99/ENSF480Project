package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class EmailHandler extends Handler {

    public EmailHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms, String username) {
        if(!input.contains("EMAIL")){
            return  false;
        }
        String parts[] = input.split("#");
        int propID = Integer.parseInt(parts[1]);
        sendString(rpms.email(propID,parts[2]));
        return true;
    }
}
