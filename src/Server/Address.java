package Server;

public class Address {
    String street;
    String city;
    String province;
    String country;
    String postalCode;
    String cityQuadrant;

    Address(String street, String city, String province, String country, String postalCode, String cityQuadrant){
        this.street = street;
        this.city = city;
        this.province = province;
        this.country = country;
        this.postalCode = postalCode;
        this.cityQuadrant = cityQuadrant;
    }

    Address(String s){
        String[] parts = s.split(" ");
        street = parts[0];
        city = parts[1];
        province = parts[2];
        country = parts[3];
        postalCode = parts[4];
        cityQuadrant = parts[5];
    }

    public String toString(){
        String sendBack = street + " " + city + " " + province + " " + country + " " + postalCode + " " + cityQuadrant;
        return  sendBack;
    }
}
