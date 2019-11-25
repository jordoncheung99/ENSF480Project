package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class ReportHandler extends Handler{

    public ReportHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms, String username) {
        if(!input.contains("REPORT")){
            return false;
        }
        String parts[] = input.split("#");
        Date start = new Date(Long.parseLong(parts[1]));
        Date end  = new Date(Long.parseLong(parts[2]));
        String send = "";
        send+= "Number of total Properties: "+rpms.reportNumProperties()+"#";

        send+= "Number of Listed Properties: "+rpms.reportNumListedProperties() + "#";
        ArrayList<String> rented = rpms.reportNumRented(start,end);
        for (String line: rented){
            send+=line+"#";
        }
        sendString(send);
        return true;
    }
}
