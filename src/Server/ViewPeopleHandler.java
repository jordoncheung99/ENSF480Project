package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ViewPeopleHandler extends Handler {
    public ViewPeopleHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms, String username) {
        if(!input.contains("VIEWPEOPLE")){
            return false;
        }
        String[] parts = input.split("#");
        String sendBack = "";
        ArrayList<Renter> Renters = rpms.viewRenters();
        ArrayList<LandLord> landLords = rpms.viewLandLords();
        for (Renter renter: Renters){
            sendBack+= "Renter: " + renter.toString()+"#";
        }

        for (LandLord landLord: landLords){
            sendBack+= "Landlord: " + landLord.toString()+"#";
        }
        sendString(sendBack);
        return false;
    }
}
