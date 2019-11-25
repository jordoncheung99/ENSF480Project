package Server;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class ChangeFeeHandler extends Handler {

    public ChangeFeeHandler(BufferedReader socketIn, PrintWriter socketOut) {
        super(socketIn, socketOut);
    }

    @Override
    public boolean doTask(String input, RPMS rpms, String username) {
        if (!input.contains("CHANGEFEE")){
            return false;
        }
        String[] parts = input.split("#");
        float feeAmmout = Float.parseFloat(parts[1]);
        int feePeriod = Integer.parseInt(parts[2]);
        rpms.setFeeAmmount(feeAmmout);
        rpms.setPeriodOfFees(feePeriod);
        sendString("Fees have been changed to " + feeAmmout + " with a period of " + feePeriod);
        return true;
    }
}
