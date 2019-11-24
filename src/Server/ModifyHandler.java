package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ModifyHandler extends Handler{

    public ModifyHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms, String username) {
        if (!input.contains("MODIFY")){
            return false;
        }
        String[] parts = input.split("#");
        int propID = Integer.parseInt(parts[1]);
        Property property = new Property(parts[2]);
        rpms.modifyListing(propID,property,username);
        sendString("Property Modified");
        return true;
    }
}
