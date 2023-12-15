package rest.section2223;

public class UserCreatePOJO {

    private String name;
    private String job;
    private String id;
    private String createdAt;

    public UserCreatePOJO(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public UserCreatePOJO() {}

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}