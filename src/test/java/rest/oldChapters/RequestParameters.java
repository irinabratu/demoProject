package rest.oldChapters;

import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestParameters {

    @Test
    public void singleQueryParam() {
        given().
                baseUri("https://postman-echo.com/").
                param("foo1","bar1").
//                queryParam("foo2","bar2").
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multipleQueryParam() {
        given().
                baseUri("https://postman-echo.com/").
//                param("foo1","bar1").
                queryParam("foo1","bar1").
                queryParam("foo2","bar2").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void hashMapQueryParam() {

        HashMap<String, String> queryParams = new HashMap<>();
        queryParams.put("foo1", "bar1");
        queryParams.put("foo2", "bar2");

        given().
                baseUri("https://postman-echo.com/").
                queryParams(queryParams).
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multiValueCommaSeparatorQueryParam() {

        given().
                baseUri("https://postman-echo.com/").
                queryParam("foo1","bar1,bar2,bar3").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multiValueSemicolonSeparatorQueryParam() {

        given().
                baseUri("https://postman-echo.com/").
                queryParam("foo1","bar1;bar2;bar3").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void pathParam() {
        given().
                baseUri("https://reqres.in/").
                pathParam("userId","1").
                log().all().
                when().
                get("/api/users/{userId}").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
