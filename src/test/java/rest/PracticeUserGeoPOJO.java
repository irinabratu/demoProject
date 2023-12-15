package rest;

public class PracticeUserGeoPOJO {

    private String lat;
    private String lng;

    public PracticeUserGeoPOJO(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public PracticeUserGeoPOJO(){}

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
