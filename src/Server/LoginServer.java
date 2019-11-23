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

    public void addUser(String username, String password, String type, Name name){
        //TODO Pass in address and name objects
        try {
            Connection conn = database.getConnection();
            /*
            PreparedStatement addAddress = conn.prepareStatement("INSERT INTO address VALUES(?, ?, ?, ?, ?)");
            addAddress.setString(1, address.postalCode);
            addAddress.setString(2, address.country);
            addAddress.setString(3, address.province);
            addAddress.setString(4, address.city);
            addAddress.setString(5, address.street);
            */

            PreparedStatement addUser = conn.prepareStatement("INSERT INTO User VALUES(?, ?, ?)");
            addUser.setString(1, username);
            addUser.setString(2, password);
            addUser.setString(3, type);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public User validate(String username, String password){
        try {
            Connection conn = database.getConnection();
            PreparedStatement validateUsers = conn.prepareStatement("SELECT * FROM User WHERE Uname = ? AND Pass = ?");
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
        for(User temp: users){
            if(temp.username.equals(username)){
                return true;
            }
        }
        return false;
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
