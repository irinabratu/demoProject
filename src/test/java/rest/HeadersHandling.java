package rest;

import io.restassured.http.Header;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Headers {

    @Test
    public void include_headers() {

        Header header = new Header("headerName", "value2");
        Header matchHeader = new Header("x-mock-match-request-headers","headerName");

        given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                header(header).
                header(matchHeader).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void include_headers_as_obj() {


        Header header = new Header("headerName", "value2");
        Header matchHeader = new Header("x-mock-match-request-headers","headerName");

        Headers headers = new Headers(header,matchHeader);

        given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                header(header).
                header(matchHeader).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }
}
