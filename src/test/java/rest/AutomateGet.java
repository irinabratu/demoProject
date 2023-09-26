package rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AutomateGet {

    @Test
    public void validate_get_status_code() {
        given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products/4643").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_get_response_body() {
        given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products/4643").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                body("name", is(equalTo("Starbucks Coffee Variety Pack, 100% Arabica")),
                        "manufacturer", is(equalTo("Starbucks")));
    }

    @Test
    public void validate_get_response_body_array() {
        given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                body("category", hasItems("dairy", "bread-bakery"),
                        "[0].id", equalTo(4643),
                        "category.size()", equalTo(20));
    }

    @Test
    public void extract_response() {
        Response response = given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                extract().response();
//        System.out.println(response.asString());
    }

    @Test
    public void extract_single_value_from_response() {
        Response response = given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products").
                then().
//                log().
//                all().
        assertThat().
                        statusCode(200).
                        extract().response();

        //option 1
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("option1: " + jsonPath.getString("[1].name"));

        //option 2
        System.out.println("option2: " + JsonPath.from(response.asString()).getString("[1].name"));

        //option 3
        System.out.println("option3: " + response.path("[1].name").toString());

        //option 4
        String name = given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products").
                then().
        assertThat().
                        extract().response().path("[1].name");
        System.out.println("option4: " + name);

        //assert with Hamcrest
        assertThat(name, equalTo("Ethical Bean Medium Dark Roast, Espresso"));
    }
}
