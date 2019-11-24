package Server;

public class Address {
    private String street;
    private String city;
    private String province;
    private String country;
    private String postalCode;
    private String cityQuadrant;

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

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityQuadrant(String cityQuadrant) {
        this.cityQuadrant = cityQuadrant;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public String toString(){
        String sendBack = street + " " + city + " " + province + " " + country + " " + postalCode + " " + cityQuadrant;
        return  sendBack;
    }
}
