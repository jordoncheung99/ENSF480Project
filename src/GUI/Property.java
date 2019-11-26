package GUI;

import Server.Address;

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

    public Property(float rentAmmount, float rentTerm, float area, int numOfBathRooms, int numOfBedRooms, boolean furnished, Address address, String typeOfProperty, int listID, boolean active, boolean rented, boolean suspended, Date dateRented, Date datePaid){
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

    public boolean getFurnished() { return furnished; }

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

    public boolean getSuspended() { return suspended; }

    public Date getDateRented() { return dateRented; }

    public Date getDatePaid() { return datePaid; }

    public Property(String input){
        String[] parts = input.split("&");
        rentAmmount = Float.parseFloat(parts[0]);
        rentTerm = Float.parseFloat(parts[1]);
        area = Float.parseFloat(parts[2]);
        numOfBedRooms = Integer.parseInt(parts[3]);
        numOfBathRooms = Integer.parseInt(parts[4]);
        furnished = Boolean.parseBoolean(parts[5]);
        address = new Address(parts[6]);
        typeOfProperty = parts[7];
        listID = Integer.parseInt(parts[8]);
        active = Boolean.parseBoolean(parts[9]);
        rented = Boolean.parseBoolean(parts[10]);
        suspended = Boolean.parseBoolean(parts[11]);
        if (parts[12].equals(0)){
            dateRented = null;
        }else{
            dateRented = new Date(Long.parseLong(parts[12]));
        }

        if (parts[13].equals("0")){
            datePaid = null;
        }else{
            datePaid = new Date(Long.parseLong(parts[13]));
        }
    }

    public String toServerString(){
        String sendBack = "";
        sendBack += rentAmmount;
        sendBack += "&" + rentTerm;
        sendBack += "&" + area;
        sendBack += "&" + numOfBedRooms;
        sendBack += "&" + numOfBathRooms;
        sendBack += "&" + furnished;
        sendBack += "&" + address.toString();
        sendBack += "&" + typeOfProperty;
        sendBack += "&" + listID;
        sendBack += "&" + active;
        sendBack += "&" + rented;
        sendBack += "&" + suspended;
        if(dateRented == null){
            sendBack += "&" + 0;
        }else{
            sendBack += "&" + dateRented.getTime();
        }

        if(datePaid == null){
            sendBack += "&" + 0;
        }else{
            sendBack += "&" + datePaid.getTime();
        }
        return  sendBack;
    }

    public String toString(){
        String sendBack = "Property ID: " + listID + " Rent Amount: " + rentAmmount + " Rent Term: " + rentTerm + " Area: " + area + " Bedrooms: " + numOfBedRooms + " Bathrooms: " + numOfBathRooms + " Furnished: " + furnished;
        sendBack += " Address: " + address.toString() + " Type: " + typeOfProperty + " Is Active: " +  active  + " Is Rented: " +  rented + " Is Suspended: " + suspended;
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
