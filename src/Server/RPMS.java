package Server;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class RPMS {
    private int periodOfFees;
    private float feeAmmount;
    private int numProperties;
    private int numRented;
    private int numListed;
    PropertyListing listing;
    ArrayList<Observer> observers;
    LoginServer loginServer;
    private Filter filter;


    public  static  void main(String args[]){
        RPMS rpms = new RPMS();
        System.out.println("num Props: " + rpms.numProperties);
        System.out.println("num Rented: " + rpms.numRented );
        System.out.println("num Listed: " + rpms.numListed);
        Address address = new Address("street", "Calgary", "AB", "Canada", "T3k9D2");
        Property property = new Property(100,200,100,1,2,true,address,"Condo", 123, true, false, false, null,null);
        rpms.addNewProperty(property);
        rpms.payFee(200,123);
        rpms.payFee(200,123);
        rpms.payFee(200,123);
        //rpms.modifyListing(123,property);
        rpms.listing.saveDataBase();
    }

    RPMS(){
        observers = new ArrayList<>();
        listing = new PropertyListing();
        listing.loadDataBase();
        periodOfFees = 100;
        feeAmmount = 200;
        numProperties = listing.getSize();
        numRented = listing.getNumRented();
        numListed = listing.getNumActive();
        loginServer = LoginServer.getInstance();
    }

    public ArrayList<Property> filterSearch(ArrayList<Criteria> criteria){
        //TODO implement filterSearch
        return null;
    }

    public void addNewProperty(Property property){
        listing.addProperty(property);
        notifyObservers(property);
    }

    public void modifyListing(int propertyID, Property property){
        //TODO implement safty check to make sure the owner of the property or manage are the only one that can change it
        listing.modifyProperty(propertyID,property);
        notifyObservers(property);
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

    public String payFee(float paidAmmount, int proprtyToActive){
        if (paidAmmount != feeAmmount){
            //System.out.println("That is not the right ammount! Pay: " + feeAmmount);
            return  "That is not the right ammount! Pay: " + feeAmmount;
        }
        Property prop = listing.findID(proprtyToActive);
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
        return "property activated!";
    }

    public int reportNumProperties(){
        numProperties = listing.getSize();
        return numProperties;
    }

    public int reportNumListedProperties(){
        numListed = listing.getNumActive();
        return numListed;
    }

    public int reportNumRented(){
        numRented = listing.getNumRented();
        return numRented;
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



}
