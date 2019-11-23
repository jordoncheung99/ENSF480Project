    import java.io.*;
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
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

    public void addUser(String username, String password, String type){
        //TODO translate to work with database

        //Check if the username exists
        for(User temp: users){
            if(temp.username.equals(username)){
                System.out.println("Username already exists, please pick another name");
                return;
            }
        }
        //TODO: Translate to work with a database.
        users.add(new User(username, password, type));
    }

    public User validate(String username, String password){
        try {
            database = new MySQLDatabase();
            database.initializeConnection();
            Connection conn = database.getConnection();
            Statement validateUsers = conn.createStatement();
            String query = "SELECT * FROM User WHERE Uname = '" + username + "' AND Pass = '" + password + "'";
            ResultSet resSet = validateUsers.executeQuery(query);
            if(!resSet.next()){
                return null;
            }
            else{
                User temp = new User(resSet.getString("Uname"), resSet.getString("Pass"), null);
                return temp;
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println("Error - Could not execute query");
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

    public void saveDataBase(){
//        //TODO: Translate to work with database
//        try {
//            PrintWriter writer = new PrintWriter("Users.txt");
//            for(User temp: users){
//                writer.println(temp.username + " " + temp.password + " " + temp.type);
//            }
//            writer.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

}
