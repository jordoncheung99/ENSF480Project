package Server;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

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
        observers = new ArrayList();
        listing = new PropertyListing();
        listing.loadDataBase();
        periodOfFees = 100;
        feeAmmount = 200;
        numProperties = listing.getSize();
        numListed = listing.getNumActive();
        loginServer = LoginServer.getInstance();
        filter = new BureFroceFilter();
    }

    public ArrayList<Property> filterSearch(Criteria criteria) {
        listing.loadDataBase();
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

    public String modifyListing(int propertyID, Property property, String username){
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM property WHERE listID = ? AND landlord = ?");
            state.setInt(1, propertyID);
            state.setString(2, username);
            ResultSet landlordSet = state.executeQuery();
            if(landlordSet.next()) {
                listing.modifyProperty(propertyID, property);
                state = conn.prepareStatement("UPDATE Property SET landlord = ?, area = ?, rentAmount = ?, rentTerm = ?, numOfBedRooms = ?, numOfBathRooms = ?, address = ?, typeOfProperty = ?, active = ?, rented = ?, suspended = ?, dateRented = ?, datePaid = ?, furnished = ? WHERE listID = ?");
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
                if (property.getDatePaid() == null) {
                    state.setDate(13, null);
                } else {
                    state.setDate(13, new java.sql.Date(property.getDatePaid().getTime()));
                }
                state.setBoolean(14, property.getFurnished());
                state.setInt(15, propertyID);
                state.executeUpdate();
                notifyObservers(property);
                return "Property Added";
            }
            else{
                return "You cannot modify this property";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Cannot modify property";
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
        if (paidAmmount != getFeeAmount()){
            //System.out.println("That is not the right ammount! Pay: " + feeAmmount);
            return  "That is not the right ammount! Pay: " + getFeeAmount();
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
        numProperties = 0;
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM property");
            ResultSet resSet = state.executeQuery();
            while(resSet.next()){
                numProperties ++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return numProperties;
    }

    public int reportNumListedProperties(){
        numListed = 0;
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM property WHERE active = true");
            ResultSet resSet = state.executeQuery();
            while(resSet.next()){
                numListed ++;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return numListed;
    }

    public ArrayList<String> reportNumRented(Date start, Date end){

        ArrayList<String> send = new ArrayList<>();
        int count = 0;
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM property WHERE dateRented > ? AND dateRented < ?");
            state.setDate(1, new java.sql.Date(start.getTime()));
            state.setDate(2, new java.sql.Date(end.getTime()));
            System.out.println(new java.sql.Date(start.getTime()).toString());
            System.out.println(new java.sql.Date(end.getTime()).toString());
            ResultSet resSet = state.executeQuery();
            while(resSet.next()){
                state = conn.prepareStatement("SELECT * FROM Address WHERE PostalCode = ?");
                state.setString(1, resSet.getString("address"));
                ResultSet addressSet = state.executeQuery();
                addressSet.next();
                Address address = new Address(addressSet.getString("Street"), addressSet.getString("City"), addressSet.getString("Province"), addressSet.getString("Country"), addressSet.getString("PostalCode"));
                Property p = new Property(resSet.getFloat("rentAmount"), resSet.getFloat("rentTerm"), resSet.getFloat("area"),
                        resSet.getInt("numOfBathRooms"), resSet.getInt("numOfBedRooms"), resSet.getBoolean("furnished"),
                        address, resSet.getString("typeOfProperty"), resSet.getInt("listId"),
                        resSet.getBoolean("active"), resSet.getBoolean("rented"), resSet.getBoolean("Suspended"),
                        resSet.getDate("dateRented"), resSet.getDate("datePaid")
                );
                String s = "Landlord: " + resSet.getString("landlord") + p.toString();
                send.add(s);
            }
            return send;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<LandLord>  viewLandLords(){
        ArrayList<LandLord> landlords = new ArrayList<LandLord>();
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM Person WHERE PersonRole = ?");
            state.setString(1, "LANDLORD");
            ResultSet resSet = state.executeQuery();
            while(resSet.next()){
                landlords.add(new LandLord(database, resSet.getString("Uname")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return landlords;
    }

    public ArrayList<Renter> viewRenters(){
        ArrayList<Renter> renters = new ArrayList<Renter>();
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM Person WHERE PersonRole = ?");
            state.setString(1, "RENTER");
            ResultSet resSet = state.executeQuery();
            while(resSet.next()){
                renters.add(new Renter(database, resSet.getString("Uname")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return renters;
    }

    /**
     *
     * @param propID property id itself
     * @param message the email body
     * @return success/error message
     */
    public String email(int propID, String message, String username){
        String to = "";
        Connection conn;
        PreparedStatement state;
        conn = database.getConnection();
        String password;
        try {
            state = conn.prepareStatement("SELECT landlord FROM property WHERE listID = ?");
            state.setInt(1, propID);
            ResultSet resSet = state.executeQuery();
            resSet.next();
            to = resSet.getString("landlord");
            state = conn.prepareStatement("SELECT * FROM person WHERE Uname = ?");
            state.setString(1, username);
            resSet = state.executeQuery();
            resSet.next();
            password = resSet.getString("Pass");
            System.out.println(username + " " + password);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(username));
            emailMessage.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            emailMessage.setSubject("RPMS Inquiry About Property With ID: " + propID);
            emailMessage.setText(message);

            Transport.send(emailMessage);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Email Sent";
    }

    public String email(int propID, String message, String username, String password){
        String to = "";
        Connection conn;
        PreparedStatement state;
        conn = database.getConnection();
        try {
            state = conn.prepareStatement("SELECT landlord FROM property WHERE listID = ?");
            state.setInt(1, propID);
            ResultSet resSet = state.executeQuery();
            resSet.next();
            to = resSet.getString("landlord");
            System.out.println(username + " " + password);

            Properties prop = new Properties();
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message emailMessage = new MimeMessage(session);
                emailMessage.setFrom(new InternetAddress(username));
                emailMessage.setRecipients(
                        Message.RecipientType.TO,
                        InternetAddress.parse(to)
                );
                emailMessage.setSubject("RPMS Inquiry About Property With ID: " + propID);
                emailMessage.setText(message);

                Transport.send(emailMessage);

                System.out.println("Done");

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return "Email Sent";
    }


    public void setPeriodOfFees(int newPeriod){
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("UPDATE feeinfo SET period = ? WHERE feeId = 1");
            state.setInt(1, newPeriod);
            state.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void setFeeAmmount (float newFee){
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("UPDATE feeinfo SET amount = ? WHERE feeId = 1");
            state.setFloat(1, newFee);
            state.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getFeePeriod(){
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM feeinfo WHERE feeId = 1");
            ResultSet resSet = state.executeQuery();
            resSet.next();
            return resSet.getInt("period");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 1;
    }

    public float getFeeAmount(){
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM feeinfo WHERE feeId = 1");
            ResultSet resSet = state.executeQuery();
            resSet.next();
            return resSet.getInt("amount");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 200;
    }
}
