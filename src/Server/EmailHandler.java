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
        if(username != "null") {
            String parts[] = input.split("#");
            int propID = Integer.parseInt(parts[1]);
            sendString(rpms.email(propID, parts[2], username));
            return true;
        }
        else{
            String parts[] = input.split("#");
            int propID = Integer.parseInt(parts[1]);
            username = parts[3];
            String password = parts[4];
            sendString(rpms.email(propID, parts[2], username, password));
            return true;
        }
    }
}
