package rest.oldChapters;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class DefaultRequestSpecification {

    @BeforeClass
    public void beforeClass(){
        io.restassured.builder.RequestSpecBuilder requestSpecBuilder = new io.restassured.builder.RequestSpecBuilder().setBaseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").addHeader("headerName", "value2").addHeader("x-mock-match-request-headers", "headerName");
        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void validate_get_status_code_bdd() {
        Response response = get("/get").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
    }

    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);
        System.out.println("baseUrl = " + queryableRequestSpecification.getBaseUri());
        System.out.println("headers = " + queryableRequestSpecification.getHeaders());

    }
}
