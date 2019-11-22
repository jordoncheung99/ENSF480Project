public class User {
    String username;
    String password;
    int type;
    public User(String uname, String pass, int type){
        username = uname;
        password = pass;
        this.type = type;
    }

    int getType(){
        return type;
    }
}
