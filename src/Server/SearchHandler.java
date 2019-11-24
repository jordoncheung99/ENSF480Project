package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SearchHandler extends Handler {

    public SearchHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms) {
        if(!input.contains("SEARCH")){
            return false;
        }
        String[] criteriaRaw = Handler.processString(input);
        ArrayList<Criteria> criteria = new ArrayList<>();
        for(int i = 1; i <criteriaRaw.length; i++){
            criteria.add( new Criteria(criteriaRaw[i]));
        }
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
