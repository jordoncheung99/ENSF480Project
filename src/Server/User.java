package Server;

public class User {
    public String username;
    public String password;
    public String type;
    public User(String uname, String pass, String type){
        username = uname;
        password = pass;
        this.type = type;
    }

    String getType(){
        return type;
    }
}
