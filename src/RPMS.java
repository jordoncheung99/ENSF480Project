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
    private Filter filter;


    public  static  void main(String args[]){
        RPMS rpms = new RPMS();
        System.out.println("num Props: " + rpms.numProperties);
        System.out.println("num Rented: " + rpms.numRented );
        System.out.println("num Listed: " + rpms.numListed);
        Address address = new Address("street", "Calgary", "AB", "Canada", "T3k9D2", "NW");
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
    }

    public PropertyListing filterSearch(Criteria criteria){
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

    public boolean payFee(int paidAmmount, int proprtyToActive){
        if (paidAmmount != feeAmmount){
            System.out.println("That is not the right ammount! Pay: " + feeAmmount);
            return  false;
        }
        if(listing.findID(proprtyToActive).datePaid != null){
            System.out.println("The property has already been paid for!");
            return  false;
        }
        listing.findID(proprtyToActive).datePaid = new Date(Instant.now().getEpochSecond());
        return  true;
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



}
