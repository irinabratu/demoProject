package rest;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class CRUD {

    @BeforeClass
    public void beforeClass() {
        io.restassured.builder.RequestSpecBuilder requestSpecBuilder = new io.restassured.builder.RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("https://reqres.in").
                setBasePath("/api").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
//                expectStatusCode(200).
//                expectStatusCode(201).
                expectStatusCode(204).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePost() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";

        given().
                body(payload).
                when().
                post("/users").
                then().
                assertThat().
                body("name", equalTo("morpheus"),"createdAt", matchesPattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]+(:[0-9]+)+)\\.[A-Za-z0-9]+$"));
    }

    @Test
    public void validatePut() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given().
                body(payload).
                when().
                put("/users/2").
                then().
                assertThat().
                body("job", equalTo("zion resident"),"updatedAt", matchesPattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]+(:[0-9]+)+)\\.[A-Za-z0-9]+$"));
    }


    @Test
    public void validateDelete() {
        given().
                delete("/users/2");
    }
}
