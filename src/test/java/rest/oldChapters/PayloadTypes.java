package rest.oldChapters;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class PayloadTypes {

    @BeforeClass
    public void beforeClass() {
        io.restassured.builder.RequestSpecBuilder requestSpecBuilder = new io.restassured.builder.RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("https://reqres.in").
                setBasePath("/api").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
//                expectStatusCode(201).
//        expectStatusCode(204).
                        log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @org.testng.annotations.Test
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

    @Test
    public void validatePutJSONFilePayload() {
        File file = new File("src/main/resources/CreateUserPayload.json");

        given().
                body(file).
                when().
                put("/users/2").
                then().
                assertThat().
                body("job", equalTo("leader"),"updatedAt", matchesPattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]+(:[0-9]+)+)\\.[A-Za-z0-9]+$"));
    }

    @Test
    public void validatePutMapPayload() {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","morpheus");
        map.put("job","leader");


        given().
                body(map).
                when().
                put("/users/2").
                then().
                assertThat().
                body("job", equalTo("leader"),"updatedAt", matchesPattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]+(:[0-9]+)+)\\.[A-Za-z0-9]+$"));
    }

    @Test
    public void validatePostComplexJSONPayload() {

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string Photo One");

        HashMap<String, Object> categories = new HashMap<>();
        categories.put("id", 1);
        categories.put("name","string");

        HashMap<String, Object> tagsMap = new HashMap<>();
        tagsMap.put("id", 1);
        tagsMap.put("name","string");

        List<Object> tagsArray = new ArrayList<>();
        tagsArray.add(tagsMap);

        HashMap<String, Object> mainMap = new HashMap<>();
        mainMap.put("id", 1);
        mainMap.put("category", categories);
        mainMap.put("name", "doggie");
        mainMap.put("photoUrls", photoUrls);
        mainMap.put("tags", tagsArray);
        mainMap.put("status", "available");

        given().
                body(mainMap).
                when().
                post("https://petstore.swagger.io/v2/pet").
                then().
                assertThat().
                body("name", equalTo("doggie"));
    }
}
