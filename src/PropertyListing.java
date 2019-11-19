import java.io.*;
import java.util.ArrayList;

public class PropertyListing {
    public  static  void main(String args[]){
        PropertyListing listings = new PropertyListing();
        listings.loadDataBase();
        Address address = new Address("street", "Calgary", "AB", "Canada", "T3K9D2", "NW");
        Property property = new Property(100,2,100,2,1,true,address, "Condo", 123);
        listings.addProperty(property);
        listings.saveDataBase();
    }

    private ArrayList<Property> properties;

    PropertyListing(){
        properties = new ArrayList<>();
    }

    void addProperty(Property property){
        //Safty Check to make sure the same ID isn't in the system already
        for (Property prop: properties){
            if (prop.getListID() == property.getListID()){
                System.out.println("That proprty ID already exists in the system!");
                return;
            }
        }
        properties.add(property);
    }

    ArrayList<Property> getProperties(){
        return properties;
    }

    public void loadDataBase(){
        //TODO: Translate to work with databse
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("Properties.txt"));
            String line = null;
            while( (line = in.readLine())!= null){
                String[] parts = line.split(" ");
                Address address = new Address(parts[6],parts[7],parts[8],parts[9],parts[10],parts[11]);
                float rentAmmount = Float.parseFloat(parts[0]);
                float rentTerm = Float.parseFloat(parts[1]);
                float area = Float.parseFloat(parts[2]);
                int numOfBedRooms = Integer.parseInt(parts[3]);
                int numOfBathRoom = Integer.parseInt(parts[4]);
                boolean furnished = Boolean.parseBoolean(parts[5]);
                String typeOfProperty = parts[12];
                int listID = Integer.parseInt(parts[13]);
                properties.add(new Property(rentAmmount,rentTerm,area,numOfBedRooms,numOfBathRoom,furnished,address,typeOfProperty,listID));
                //properties.add(new Property(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5], add, parts[6],parts[6],parts[6],parts[6],));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Text file not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Something went wrong while reading the file");
            e.printStackTrace();
        }
    }

    public void saveDataBase(){
        //TODO: Translate to work with database
        try {
            PrintWriter writer = new PrintWriter("Properties.txt");
            for(Property prop: properties){
                writer.println(prop.toString());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
