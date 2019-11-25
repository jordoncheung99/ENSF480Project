package Server;

public class Person{
    Name name;
    Address address;

    public String toString(){
        String send = "Name: " + name.toString();
        send += " Address: " + address.toString();
        return send;
    }
}
