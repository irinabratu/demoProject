package rest;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class RequestSpecBuilder {

    RequestSpecification request;
    ResponseSpecification response;

    @BeforeClass
    public void beforeClass() {
        io.restassured.builder.RequestSpecBuilder requestSpecBuilder = new io.restassured.builder.RequestSpecBuilder().
                setBaseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                addHeader("headerName", "value2").
                addHeader("x-mock-match-request-headers", "headerName").
                log(LogDetail.ALL);
        request = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        response = responseSpecBuilder.build();
    }

    @Test
    public void validate_get_status_code_bdd() {
        given().spec(request).get("/get").then().spec(response);
    }
}
