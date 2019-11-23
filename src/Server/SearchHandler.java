package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class SearchHandler extends Handler {

    public SearchHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input) {
        if(!input.contains("SEARCH")){
            return false;
        }
        String[] criteriaRaw = Handler.processString(input);
        ArrayList<Criteria> criteria = new ArrayList<>();
        for(int i = 1; i <criteriaRaw.length; i++){
            criteria.add( new Criteria(criteriaRaw[i]));
        }
        System.out.println(criteria.get(0).toServerString());
        //TODO: Call Search Function!
        sendString("Sure did search!");
        return true;
    }
}
