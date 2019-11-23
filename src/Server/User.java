package Server;

public class User {
    String username;
    String password;
    String type;
    public User(String uname, String pass, String type){
        username = uname;
        password = pass;
        this.type = type;
    }

    String getType(){
        return type;
    }
}
