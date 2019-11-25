package Server;

//import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Renter {

    private String username;
    private MySQLDatabase database;

    public Renter(MySQLDatabase database, String username){
        this.username = username;
        this.database = database;
        this.database.initializeConnection();
    }

    public String displayRenter(){
        String s = "";
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM person WHERE Uname = ?");
            state.setString(1, username);
            ResultSet resSet = state.executeQuery();
            resSet.next();
            s = "Username: " + resSet.getString("Uname") + " Name: " + resSet.getString("FirstName") + " " + resSet.getString("LastName") + " Role: " + resSet.getString("PersonRole");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return s;
    }
}
