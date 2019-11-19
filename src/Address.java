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

    public String toString(){
        String sendBack = street + " " + city + " " + province + " " + country + " " + postalCode + " " + cityQuadrant;
        return  sendBack;
    }
}
