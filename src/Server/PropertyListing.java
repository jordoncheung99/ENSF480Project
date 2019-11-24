package Server;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PropertyListing {
    //TODO Update to work with a database
    private ArrayList<Property> properties;
    private MySQLDatabase database;

    PropertyListing(){
        properties = new ArrayList<>();
        database = new MySQLDatabase();
        database.initializeConnection();
    }

    public int getSize(){
        return properties.size();
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
        properties = new ArrayList<Property>();
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM Property");
            ResultSet resSet = state.executeQuery();
            while(resSet.next())
            {
                state = conn.prepareStatement("SELECT * FROM Address WHERE PostalCode = ?");
                state.setString(1, resSet.getString("address"));
                ResultSet addressSet = state.executeQuery();
                addressSet.next();
                Address address = new Address(addressSet.getString("Street"), addressSet.getString("City"), addressSet.getString("Province"), addressSet.getString("Country"), addressSet.getString("PostalCode"));
                properties.add(new Property(resSet.getFloat("rentAmount"), resSet.getFloat("rentTerm"), resSet.getFloat("area"),
                        resSet.getInt("numOfBathRooms"), resSet.getInt("numOfBedRooms"), resSet.getBoolean("furnished"),
                        address, resSet.getString("typeOfProperty"), resSet.getInt("listId"),
                        resSet.getBoolean("active"), resSet.getBoolean("rented"), resSet.getBoolean("Suspended"),
                        resSet.getDate("dateRented"), resSet.getDate("datePaid")
                ));
            }
        }catch(SQLException e){
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
