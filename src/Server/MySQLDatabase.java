package Server;

import java.sql.*;

public class MySQLDatabase implements DatabaseCredentials {
    private Connection conn;

    public void initializeConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DatabaseCredentials.DB_URL, DatabaseCredentials.USERNAME, DatabaseCredentials.PASSWORD);
        }catch(SQLException | ClassNotFoundException e){
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