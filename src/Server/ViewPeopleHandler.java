package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ViewPeopleHandler extends Handler {
    public ViewPeopleHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms) {
        sendString("NEEDED TO BE IMPLMENTED");
        return false;
    }
}
