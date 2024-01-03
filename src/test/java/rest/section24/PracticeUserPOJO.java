package rest.section24;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "id", allowSetters = true)
public class PracticeUserPOJO {

    private String name;
    private String username;
    private String email;
    private PracticeUserAddressPOJO address;
    private PracticeUserGeoPOJO geo;

    private String id;

    public PracticeUserPOJO(String name, String username, String email, PracticeUserAddressPOJO address, PracticeUserGeoPOJO geo) {
        this.name=name;
        this.username=username;
        this.email=email;
        this.address=address;
        this.geo=geo;
    }

    public static PracticeUserPOJOBuilder builder() {
        return new PracticeUserPOJOBuilder();
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public PracticeUserAddressPOJO getAddress() {
        return this.address;
    }

    public PracticeUserGeoPOJO getGeo() {
        return this.geo;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(PracticeUserAddressPOJO address) {
        this.address = address;
    }

    public void setGeo(PracticeUserGeoPOJO geo) {
        this.geo = geo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class PracticeUserPOJOBuilder {
        private String name;
        private String username;
        private String email;
        private PracticeUserAddressPOJO address;
        private PracticeUserGeoPOJO geo;
        private String id;

        PracticeUserPOJOBuilder() {
        }

        public PracticeUserPOJOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PracticeUserPOJOBuilder username(String username) {
            this.username = username;
            return this;
        }

        public PracticeUserPOJOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public PracticeUserPOJOBuilder address(PracticeUserAddressPOJO address) {
            this.address = address;
            return this;
        }

        public PracticeUserPOJOBuilder geo(PracticeUserGeoPOJO geo) {
            this.geo = geo;
            return this;
        }

        public PracticeUserPOJOBuilder id(String id) {
            this.id = id;
            return this;
        }

        public PracticeUserPOJO build() {
            return new PracticeUserPOJO(name, username, email, address, geo);
        }

        public String toString() {
            return "PracticeUserPOJO.PracticeUserPOJOBuilder(name=" + this.name + ", username=" + this.username + ", email=" + this.email + ", address=" + this.address + ", geo=" + this.geo + ", id=" + this.id + ")";
        }
    }
}
