import java.util.ArrayList;

public class RPMS {
    private int periodOfFees;
    private float feeAmmount;
    private int numProperties;
    private int numRented;
    private int numListed;
    PropertyListing listing;
    ArrayList<Observer> observers;
    private Filter filter;

    public PropertyListing filterSearch(Criteria criteria){
        //TODO implement filterSearch
        return null;
    }

    public void addNewProperty(Property property){
        //TODO implement add Property
    }

    public void modifyListing(int propertyID, Property property){
        //TODO implement modification
    }

    public void registerObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        //TODO implement notify Observers
        //if a listing is modifed or a new property is listed notify Observers (renters)
    }

    public void removeProperty(int propertyID){
        //TODO implement remove property
    }

    public boolean payFee(int paidAmmount, int proprtyToActive){
        //TODO implement payFee
        return  false;
    }

    public int reportNumProperties(){
        return numProperties;
    }

    public int reportNumListedProperties(){
        return numListed;
    }

    public int reportNumRented(){
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
