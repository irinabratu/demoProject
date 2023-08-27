package rest;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RequestSpecifications {

    Header header = new Header("headerName", "value2");
    Header matchHeader = new Header("x-mock-match-request-headers", "headerName");
    RequestSpecification request;

    @BeforeClass
    public void beforeClass(){
        request = with().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                header(header).
                header(matchHeader).
                log().all();
    }

    @Test
    public void get_request_spec() {
        RequestSpecification requestSpec = given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                header(header).
                header(matchHeader);


//        given(requestSpec).
        given().spec(requestSpec).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void get_request_spec_improved() {
        given(request).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_get_status_code_bdd() {
        Response response = request.get("/get").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }
}
