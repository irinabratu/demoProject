package rest;

import java.util.Map;

public class PracticeUserPOJO {

    private String name;
    private String username;
    private String email;
    private PracticeUserAddressPOJO address;
    private PracticeUserGeoPOJO geo;

    private int id;

    public PracticeUserPOJO(String name, String username, String email, PracticeUserAddressPOJO address, PracticeUserGeoPOJO geo) {
        this.address = address;
        this.name = name;
        this.email = email;
        this.username = username;
        this.geo = geo;
    }

    public PracticeUserPOJO(){
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public PracticeUserAddressPOJO getAddress() {
        return address;
    }

    public PracticeUserGeoPOJO getGeo() {
        return geo;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public void setAddress(PracticeUserAddressPOJO address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGeo(PracticeUserGeoPOJO geo) {
        this.geo = geo;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
