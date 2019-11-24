package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class FeeHandler extends Handler {
    public FeeHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms) {
        if(!input.contains("PAYFEE")){
            return  false;
        }
        String parts[] = input.split("#");
        float feeAmmount = Float.parseFloat(parts[1]);
        int propertyID = Integer.parseInt(parts[2]);
        sendString(rpms.payFee(feeAmmount,propertyID));
        return true;
    }
}
