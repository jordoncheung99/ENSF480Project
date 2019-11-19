import java.io.*;
import java.util.ArrayList;

public class PropertyListing {
    //TODO Update to work with a database
    private ArrayList<Property> properties;

    PropertyListing(){
        properties = new ArrayList<>();
    }

    public int getSize(){
        return properties.size();
    }

    public int getNumRented(){
        int count = 0;
        for (Property prop: properties){
            if (prop.rented){
                count++;
            }
        }
        return  count;
    }

    public  int getNumActive(){
        int count = 0;
        for(Property prop: properties){
            if(prop.active){
                count++;
            }
        }
        return count;
    }

    void modifyProperty(int listID, Property property){
        for(int i = 0; i < properties.size(); i++){
            if(properties.get(i).getListID() == listID){
                properties.set(i,property);
                return;
            }
        }
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

    void removeProperty(int propID){
        for (int i = 0; i < properties.size(); i++){
            if (properties.get(i).getListID() == propID){
                properties.remove(i);
                return;
            }
        }
    }

    ArrayList<Property> getProperties(){
        return properties;
    }

    public Property findID(int propertyID){
        for (Property prop: properties){
            if(prop.getListID() == propertyID){
                return prop;
            }
        }
        return null;
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
                boolean active = Boolean.parseBoolean(parts[14]);
                boolean rented = Boolean.parseBoolean(parts[15]);
                boolean suspended = Boolean.parseBoolean(parts[16]);
                properties.add(new Property(rentAmmount,rentTerm,area,numOfBedRooms,numOfBathRoom,furnished,address,typeOfProperty,listID, active, rented,suspended));
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
