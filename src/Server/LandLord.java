package Server;

import java.util.ArrayList;

public class LandLord extends User{
    //TODO implement landlords
    ArrayList<Integer> ownedIDs;

    public LandLord(String uname, String pass, String type) {
        super(uname, pass, type);
    }
}
