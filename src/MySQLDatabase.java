import java.sql.*;

public class MySQLDatabase implements DatabaseCredentials{
    private Connection conn;

    public void initializeConnection(){
        try{
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }catch(SQLException e){
            System.err.println("Error - Could not establish database connection");
        }
    }

    public void closeConnection(){
        try {
            conn.close();
        }catch(SQLException e){
            System.err.println("Error - Failed to close database connection");
        }
    }

    public Connection getConnection() {
        return conn;
    }
}