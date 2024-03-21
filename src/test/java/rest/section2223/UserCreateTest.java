package rest.section2223;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class UserCreateTest {

    RequestSpecification request;
    ResponseSpecification response;

    @BeforeClass
    public void beforeClass() {
        io.restassured.builder.RequestSpecBuilder requestSpecBuilder = new io.restassured.builder.RequestSpecBuilder().
                setContentType(ContentType.JSON).
                setBaseUri("https://reqres.in").
                setBasePath("/api").
                log(LogDetail.ALL);
        request = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                log(LogDetail.ALL);
        response = responseSpecBuilder.build();
    }

    @Test
    public void validatePost() {

        UserCreatePOJO user1 = new UserCreatePOJO("morpheus", "leader");

        given(request).
                body(user1).
        when().
                post("/users").
        then().spec(response).
                assertThat().
                body("name", equalTo(user1.getName()), "job", equalTo(user1.getJob()));
    }


    @Test
    public void validatePostTest3() throws JsonProcessingException {

        UserCreatePOJO user1 = new UserCreatePOJO("morpheus", "leader");

        UserCreatePOJO deserializedUser1 =
                given(request).
                        body(user1).
               when().
                        post("/users").
               then().spec(response).
                        extract().
                        response().
                        as(UserCreatePOJO.class);

        assertThat(deserializedUser1.getName(), equalTo(user1.getName()));
        assertThat(deserializedUser1.getCreatedAt(), matchesPattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]+(:[0-9]+)+)\\.[A-Za-z0-9]+$"));
    }

    @Test (dataProvider = "job")
    public void validatePostTest4(String name, String job) throws JsonProcessingException {

        UserCreatePOJO user1 = new UserCreatePOJO(name, job);

        UserCreatePOJO deserializedUser1 =
                given(request).
                        body(user1).
                        when().
                        post("/users").
                        then().spec(response).
                        extract().
                        response().
                        as(UserCreatePOJO.class);

        assertThat(deserializedUser1.getName(), equalTo(user1.getName()));
        assertThat(deserializedUser1.getCreatedAt(), matchesPattern("^[0-9]{4}-[0-9]{2}-[0-9]{2}T([0-9]+(:[0-9]+)+)\\.[A-Za-z0-9]+$"));
    }

    @DataProvider(name = "job")
    public Object[][] getJob() {
        return new Object[][]
                {
                        {"morpheus", "leader"},
                        {"morpheus", "follower"}
                };
    }
}
