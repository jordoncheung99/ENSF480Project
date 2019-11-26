package GUI;

public class Criteria {
    //Use -1 or null as don't care.
    //Use the enable for the boolean.
    private boolean furnishedEnable;
    private boolean furnished;
    private int numBathRooms;
    private int numBedRoom;
    private String[] typeOfProprty;
    private float area;
    private float rentTerm;
    private float rentAmmount;
    private String[] cityQuadrant;
    private int[] propertyIDs;


    public Criteria(boolean enable, boolean furnished, int numBathRooms, int numBedRoom, String[] typeOfProprty, float area, float rentTerm, float rentAmmount, String[] cityQuadrant, int[] propertyIDs){
        furnishedEnable = enable;
        this.furnished = furnished;
        this.numBathRooms = numBathRooms;
        this.numBedRoom = numBedRoom;
        this.typeOfProprty = typeOfProprty;
        this.area = area;
        this.rentTerm = rentTerm;
        this.rentAmmount = rentAmmount;
        this.cityQuadrant = cityQuadrant;
        this.propertyIDs = propertyIDs;
    }

    Criteria(String input){
        String[] parts = input.split("&");
        for(int i = 0; i < parts.length; i++){
            System.out.println(i + ": " + parts[i]);
        }
        furnishedEnable = Boolean.parseBoolean(parts[0]);
        furnished = Boolean.parseBoolean(parts[1]);
        numBathRooms = Integer.parseInt(parts[2]);
        numBedRoom = Integer.parseInt(parts[3]);

        if (!parts[4].equalsIgnoreCase("null")){
            typeOfProprty = parts[4].split(" ");
        }else{
            typeOfProprty = null;
        }


        area = Float.parseFloat(parts[5]);
        rentTerm = Float.parseFloat(parts[6]);
        rentAmmount = Float.parseFloat(parts[7]);

        if(!parts[8].equalsIgnoreCase("null")){
            cityQuadrant = parts[8].split(" ");
        }else{
            cityQuadrant = null;
        }

        if(!parts[9].equalsIgnoreCase("null")){
            String[] IDparts = parts[9].split(" ");
            propertyIDs = new int[IDparts.length];
            for(int i = 0; i < IDparts.length; i++){
                propertyIDs[i] = Integer.parseInt(IDparts[i]);
            }
        }else{
            propertyIDs = null;
        }

    }

    public String toServerString(){
        String sendback = "";
        sendback += furnishedEnable;
        sendback += "&" + furnished;
        sendback += "&" + numBathRooms;
        sendback += "&" + numBedRoom;

        if(typeOfProprty != null) {
            sendback += "&" + typeOfProprty[0];
            for (int i = 1; i < typeOfProprty.length; i++) {
                sendback += " " + typeOfProprty[i];
            }
        }else{
            sendback += "$null";
        }
        sendback += "&" + area;
        sendback += "&" + rentTerm;
        sendback += "&" + rentAmmount;

        if (cityQuadrant != null){
            sendback += "&" + cityQuadrant[0];
            for (int i = 1; i < cityQuadrant.length; i++){
                sendback += " " + cityQuadrant[i];
            }
        }else{
            sendback += "&null";
        }

        if (propertyIDs != null){
            sendback += "&" + propertyIDs[0];
            for (int i = 1; i < propertyIDs.length; i++){
                sendback += " " + propertyIDs[i];
            }

        }else{
            sendback += "&null";
        }
        return sendback;
    }


    public boolean getFurnishedEnable(){
        return furnishedEnable;
    }

    public boolean getFurnished(){
        return furnished;
    }

    public int getNumBathRooms(){
        return  numBathRooms;
    }

    public int getNumBedRoom(){
        return numBedRoom;
    }

    public  String[] getTypeOfProprty(){
        return typeOfProprty;
    }

    public float getArea(){
        return area;
    }

    public float getRentTerm(){
        return  rentTerm;
    }

    public float getRentAmmount(){
        return rentAmmount;
    }

    public String[] getCityQuadrant(){
        return cityQuadrant;
    }

    public int[] getPropertyIDs(){
        return propertyIDs;
    }
}
