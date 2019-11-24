package Server;

import Server.MySQLDatabase;
    import Server.User;

    import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class LoginServer {
    //TODO translate to work with data bases
    //TODO translate to work over a server (prob routed through RPMS)
    ArrayList<User> users;
    private MySQLDatabase database;
    private static LoginServer instance;



    private LoginServer(){
        users = new ArrayList<>();
        instance = this;
    }

    public static LoginServer getInstance() {
        if(instance == null){
            instance = new LoginServer();
        }
        return instance;
    }

    public void addUser(String username, String password, String type, Name name, Address address){
        //TODO Pass in address and name objects
        try {
            if(!exists(username)) {
                Connection conn = database.getConnection();

                PreparedStatement addAddress = conn.prepareStatement("INSERT INTO address VALUES(?, ?, ?, ?, ?)");
                addAddress.setString(1, address.postalCode);
                addAddress.setString(2, address.country);
                addAddress.setString(3, address.province);
                addAddress.setString(4, address.street);
                addAddress.setString(5, address.city);
                addAddress.executeUpdate();

                PreparedStatement addPerson = conn.prepareStatement("INSERT INTO person VALUES(?, ?, ?, ?, ?, ?)");
                addPerson.setString(1, name.first);
                addPerson.setString(2, name.last);
                addPerson.setString(3, type);
                addPerson.setString(4, address.postalCode);
                addPerson.setString(5, username);
                addPerson.setString(6, password);
                addPerson.executeUpdate();
            }
            else{
                System.out.println("Username already exists");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public User validate(String username, String password){
        try {
            Connection conn = database.getConnection();
            PreparedStatement validateUsers = conn.prepareStatement("SELECT * FROM Person WHERE Uname = ? AND Pass = ?");
            validateUsers.setString(1, username);
            validateUsers.setString(2, password);
            ResultSet resSet = validateUsers.executeQuery();
            if(!resSet.next()){
                return null;
            }
            else{
                User temp = new User(resSet.getString("Uname"), resSet.getString("Pass"), null);
                return temp;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(String username){
        try {
            Connection conn = database.getConnection();
            PreparedStatement state = conn.prepareStatement("SELECT * FROM Person WHERE Uname = ?");
            state.setString(1, username);
            ResultSet resSet = state.executeQuery();
            if(resSet.next()) {
                return true;
            }
            return false;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public void removeUser(String username, String password){
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).username.equals(username) && users.get(i).password.equals(password)){
                users.remove(i);
                return;
            }
        }
    }

    public void loadDataBase(){
        database = new MySQLDatabase();
        database.initializeConnection();
    }

}
