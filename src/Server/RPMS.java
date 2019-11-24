package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class RPMS {
    private int periodOfFees;
    private float feeAmmount;
    private int numProperties;
    private int numListed;
    private MySQLDatabase database;
    PropertyListing listing;
    ArrayList<Observer> observers;
    LoginServer loginServer;
    private Filter filter;


    public  static  void main(String args[]){
        RPMS rpms = new RPMS();
        System.out.println("num Props: " + rpms.numProperties);
        System.out.println("num Listed: " + rpms.numListed);
        Address address = new Address("street", "Calgary", "AB", "Canada", "T3k9D2");
        Property property = new Property(100,200,100,1,2,true,address,"Condo", 123, true, false, false, null,null);
        rpms.addNewProperty(property, "bla");
        rpms.payFee(200,123);
        rpms.payFee(200,123);
        rpms.payFee(200,123);
        //rpms.modifyListing(123,property);
        rpms.listing.saveDataBase();
    }

    RPMS(){
        database = new MySQLDatabase();
        database.initializeConnection();
        observers = new ArrayList<>();
        listing = new PropertyListing();
        listing.loadDataBase();
        periodOfFees = 100;
        feeAmmount = 200;
        numProperties = listing.getSize();
        numListed = listing.getNumActive();
        loginServer = LoginServer.getInstance();
        filter = new BureFroceFilter();
    }

    public ArrayList<Property> filterSearch(ArrayList<Criteria> criteria){
        return filter.Filter(listing,criteria);
    }

    public void addNewProperty(Property property, String username) {
        try {
            listing.addProperty(property);
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("INSERT INTO Property VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            state.setString(1, username);
            state.setFloat(2, property.getArea());
            state.setFloat(3, property.getRentAmmount());
            state.setFloat(4, property.getRentTerm());
            state.setInt(5, property.getNumOfBedRooms());
            state.setInt(6, property.getNumOfBathRooms());
            state.setString(7, property.getAddress().getPostalCode());
            state.setString(8, property.getTypeOfProperty());
            state.setBoolean(9, property.getActive());
            state.setBoolean(10, property.getRented());
            state.setBoolean(11, property.getSuspended());
            state.setDate(12, new java.sql.Date(property.getDateRented().getTime()));
            if(property.getDatePaid() == null){
                state.setDate(13, null);
            }else {
                state.setDate(13, new java.sql.Date(property.getDatePaid().getTime()));
            }
            state.setBoolean(14, property.getFurnished());
            state.executeUpdate();

            state = conn.prepareStatement("INSERT INTO address VALUES(?, ?, ?, ?, ?)");
            state.setString(1, property.getAddress().getPostalCode());
            state.setString(2, property.getAddress().getCountry());
            state.setString(3, property.getAddress().getProvince());
            state.setString(4, property.getAddress().getStreet());
            state.setString(5, property.getAddress().getCity());
            state.executeUpdate();

            notifyObservers(property);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyListing(int propertyID, Property property, String username){
        try {
            listing.modifyProperty(propertyID, property);
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("UPDATE Property SET landlord = ?, area = ?, rentAmount = ?, rentTerm = ?, numOfBedRooms = ?, numOfBathRooms = ?, address = ?, typeOfProperty = ?, active = ?, rented = ?, suspended = ?, dateRented = ?, datePaid = ?, furnished = ? WHERE listID = ?");
            state.setString(1, username);
            state.setFloat(2, property.getArea());
            state.setFloat(3, property.getRentAmmount());
            state.setFloat(4, property.getRentTerm());
            state.setInt(5, property.getNumOfBedRooms());
            state.setInt(6, property.getNumOfBathRooms());
            state.setString(7, property.getAddress().getPostalCode());
            state.setString(8, property.getTypeOfProperty());
            state.setBoolean(9, property.getActive());
            state.setBoolean(10, property.getRented());
            state.setBoolean(11, property.getSuspended());
            state.setDate(12, new java.sql.Date(property.getDateRented().getTime()));
            if(property.getDatePaid() == null){
                state.setDate(13, null);
            }else {
                state.setDate(13, new java.sql.Date(property.getDatePaid().getTime()));
            }
            state.setBoolean(14, property.getFurnished());
            state.setInt(15, propertyID);
            state.executeUpdate();
            notifyObservers(property);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(Property property){
        //TODO implement notify Observers
        //if a listing is modifed or a new property is listed notify Observers (renters)
    }

    public void removeProperty(int propertyID){
        listing.removeProperty(propertyID);
    }

    public String payFee(float paidAmmount, int propertyToActive){
        listing.loadDataBase();
        if (paidAmmount != feeAmmount){
            //System.out.println("That is not the right ammount! Pay: " + feeAmmount);
            return  "That is not the right ammount! Pay: " + feeAmmount;
        }
        Property prop = listing.findID(propertyToActive);
        if(prop == null){
            //System.out.println("That property dosen't exist!");
            return "That property dosen't exist!";
        }

        if(prop.datePaid != null){
//            System.out.println("The property has already been paid for!");
            return  "The property has already been paid for!";
        }

        prop.datePaid = new Date(Instant.now().getEpochSecond());
        prop.active = true;
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("UPDATE Property SET datePaid = ?, active = true WHERE listID = ?");
            state.setDate(1, new java.sql.Date(prop.datePaid.getTime()));
            state.setInt(2, propertyToActive);
            state.executeUpdate();
            return "Property Activated!";
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Bad property activation";
    }

    public int reportNumProperties(){
        numProperties = listing.getSize();
        return numProperties;
    }

    public int reportNumListedProperties(){
        numListed = listing.getNumActive();
        return numListed;
    }

    public ArrayList<String> reportNumRented(Date start, Date end){
//
//        ArrayList<String> send = new ArrayList<>();
//        int count = 0;
//        send.add(Integer.toString(count));
//        ArrayList<Property> properties = listing.getProperties();
//        ArrayList<LandLord> landLords = viewLandLords();
//        for (Property prop: properties){
//            if (prop.dateRented.getTime() > start.getTime() && prop.dateRented.getTime() < end.getTime()){
//                count++;
//                //Find land lord who owns the prop
//                String line = "";
//                for (LandLord lord: landLords){
//                    for (Integer id: lord.ownedIDs){
//                        if (id == prop.getListID()){
//                            line += lord.username;
//                        }
//                    }
//                }
//                if (line.length() == 0){
//                    line+= "N/A";
//                }
//                line += prop.getListID();
//                line += prop.getAddress().toString();
//                send.add(line);
//            }
//        }
//        send.set(0,Integer.toString(count));
//        return  send;
        return null;
    }

    public ArrayList<LandLord>  viewLandLords(){
        //TODO implement view landlord
        return null;
    }

    public ArrayList<Renter> viewRenters(){
        //TODO implement viewRenters
        return null;
    }

    /**
     *
     * @param propID property id itself
     * @param message the email body
     * @return success/error message
     */
    public String email(int propID, String message){

        //TODO implement emailing system, Guess it can be a string just print to terminal
        return "Email Sent";
    }


    public void setPeriodOfFees(int newPeriod){
        periodOfFees = newPeriod;
    }

    public void setFeeAmmount (float newFee){
        feeAmmount = newFee;
    }



}
