package rest;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONSchema {

    @Test
    public void JSONSchemaTest() {
        given().
                baseUri("https://postman-echo.com/").
                param("foo1","bar1").
                log().all().
        when().
                get("/get").
        then().
                log().all().
                assertThat().
                statusCode(200).
                body(matchesJsonSchemaInClasspath("EchoGet.json"));
    }
}
