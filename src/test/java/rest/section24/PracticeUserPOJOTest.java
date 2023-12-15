package rest.section24;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PracticeUserPOJOTest {

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

        PracticeUserGeoPOJO geo = new PracticeUserGeoPOJO("-37.3159","81.1496");
        PracticeUserAddressPOJO address = new PracticeUserAddressPOJO("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874");
        PracticeUserPOJO user = new PracticeUserPOJO("Leanne Graham", "Bret", "Sincere@april.biz", address, geo);

        given(request).
                body(user).
                when().
                post("/users").
                then().spec(response).
                assertThat().
                body("name", equalTo(user.getName()), "address.street", equalTo(user.getAddress().getStreet()));
    }
}
