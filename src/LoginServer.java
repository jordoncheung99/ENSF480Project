    import java.io.*;
import java.util.ArrayList;

public class LoginServer {
    //TODO translate to work with data bases
    //TODO translate to work over a server (prob routed through RPMS)
    ArrayList<User> users;
    private static LoginServer instance;

    public static void main(String args[]){
        LoginServer server = getInstance();
        server.loadDataBase();
        server.removeUser("Tim","321");
        server.saveDataBase();
    }

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

    public void addUser(String username, String password, int type){
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
        //TODO translate so it works with database
        for (User temp: users){
            //System.out.println("Stored Username: " + temp.username + " length: " + temp.username.length());
            //System.out.println("Stored password: " + temp.password + " length: " + temp.password.length());
            if(temp.username.equals(username) && temp.password.equals(password)){
                return temp;
            }
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
        //TODO: Translate to work with databse
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("Users.txt"));
            String line = null;
            while( (line = in.readLine())!= null){
                String[] parts = line.split(" ");
                users.add(new User(parts[0],parts[1], Integer.parseInt(parts[2])));
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
            PrintWriter writer = new PrintWriter("Users.txt");
            for(User temp: users){
                writer.println(temp.username + " " + temp.password + " " + temp.type);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
