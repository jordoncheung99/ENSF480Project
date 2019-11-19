import java.util.Date;

public class Property {
    private float rentAmmount;
    private float rentTerm;
    private float area;
    private int numOfBedRooms;
    private int numOfBathRooms;
    private boolean furnished;
    private Address address;
    private String typeOfProperty;
    private int listID;
    boolean active;
    boolean rented;
    boolean suspended;
    Date dateRented;
    Date datePaid;

    Property(float rentAmmount, float rentTerm, float area, int numOfBathRooms, int numOfBedRooms, boolean furnished, Address address, String typeOfProperty, int listID, boolean active, boolean rented, boolean suspended, Date dateRented, Date datePaid){
        this.rentAmmount = rentAmmount;
        this.rentTerm = rentTerm;
        this.area = area;
        this.numOfBedRooms = numOfBedRooms;
        this.numOfBathRooms = numOfBathRooms;
        this.furnished = furnished;
        this.address = address;
        this.typeOfProperty = typeOfProperty;
        this.listID = listID;
        this.active = active;
        this.rented = rented;
        this.suspended = suspended;
        this.dateRented = dateRented;
        this.datePaid = datePaid;
    }

    public float getRentAmmount(){
        return  rentAmmount;
    }

    public float getRentTerm(){
        return rentTerm;
    }

    public float getArea(){
        return area;
    }

    public int getNumOfBedRooms(){
        return  numOfBedRooms;
    }

    public int getNumOfBathRooms(){
        return numOfBathRooms;
    }

    public boolean getFurnished(){
        return furnished;
    }

    public Address getAddress(){
        return  address;
    }

    public String getTypeOfProperty(){
        return typeOfProperty;
    }

    public int getListID(){
        return  listID;
    }

    public boolean getActive(){
        return  active;
    }

    public boolean getRented(){
        return rented;
    }

    public String toString(){
        String sendBack = rentAmmount + " " + rentTerm + " " + area + " " + numOfBedRooms + " " + numOfBathRooms + " " + furnished;
        sendBack += " " + address.toString() + " " + typeOfProperty + " " + listID  + " " +  active  + " " +  rented + " " + suspended;
        if(dateRented == null){
            sendBack += " " + 0;
        }else{
            sendBack += " " + dateRented.getTime();
        }

        if(datePaid == null){
            sendBack += " " + 0;
        }else{
            sendBack += " " + datePaid.getTime();
        }
        System.out.println(sendBack);
        return  sendBack;
    }

}
