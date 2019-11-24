package Server;

public class Address {
    String street;
    String city;
    String province;
    String country;
    String postalCode;

    Address(String street, String city, String province, String country, String postalCode){
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
    }

    Address(String s){
        String[] parts = s.split(" ");
        street = parts[0];
        city = parts[1];
        province = parts[2];
        country = parts[3];
        postalCode = parts[4];
    }

    public String toString(){
        String sendBack = street + " " + city + " " + province + " " + country + " " + postalCode;
        return  sendBack;
    }
}
