package rest.section24;

public class PracticeUserAddressPOJO {

    private String street;
    private String suite;
    private String city;
    private String zipcode;

    public PracticeUserAddressPOJO(String street, String suite, String city, String zipcode) {
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.suite = suite;
    }

    public PracticeUserAddressPOJO(){}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}

