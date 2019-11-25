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
        if (!input.contains("VIEWPEOPLE")){
            return false;
        }
        ArrayList<LandLord> landlords = rpms.viewLandLords();
        ArrayList<Renter> renters = rpms.viewRenters();
        String sendback = "";
        for(int i = 0; i < landlords.size(); i++){
            sendback += landlords.get(i).displayLandlord()+"#";
        }
        for(int i = 0; i < renters.size(); i++){
            sendback += renters.get(i).displayRenter()+"#";
        }
        sendString(sendback);
        return true;
    }
}
