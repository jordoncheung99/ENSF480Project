package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SearchHandler extends Handler {

    public SearchHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms, String username) {
        if(!input.contains("SEARCH")){
            return false;
        }
        String[] criteriaRaw = Handler.processString(input);
        Criteria criteria = new Criteria(criteriaRaw[1]);
        ArrayList<Property> properties = rpms.filterSearch(criteria);
        String send = "";
        for (Property property: properties){
            send+=property.toServerString()+"#";
        }
        System.out.println("Sending: " + send);
        sendString(send);
        return true;
    }
}
