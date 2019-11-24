package Server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LandLord {
    private ArrayList<Property> registeredProperties;
    private MySQLDatabase database;
    public String username;

    public LandLord(MySQLDatabase database, String username){
        registeredProperties = new ArrayList<Property>();
        this.username = username;
        this.database = database;
    }

    public void updateProperties(){
        registeredProperties = new ArrayList<Property>();
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM Property WHERE landlord = ?");
            state.setString(1, username);
            ResultSet resSet = state.executeQuery();
            while(resSet.next())
            {
                state = conn.prepareStatement("SELECT * FROM Address WHERE PostalCode = ?");
                state.setString(1, resSet.getString("address"));
                ResultSet addressSet = state.executeQuery();
                addressSet.next();
                Address address = new Address(addressSet.getString("Street"), addressSet.getString("City"), addressSet.getString("Province"), addressSet.getString("Country"), addressSet.getString("PostalCode"));
                registeredProperties.add(new Property(resSet.getFloat("rentAmount"), resSet.getFloat("rentTerm"), resSet.getFloat("area"),
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
    public ArrayList<Property> getRegisteredProperties(){
        return registeredProperties;
    }
}
