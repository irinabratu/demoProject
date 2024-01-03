package rest.oldChapters;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Hamcrest {

    /*
    assertion library used for unit testing along with junit
    uses matcher classes for making assertions
    code is readable, neat and intuitive
    provides thin methods like 'is' and 'not', decorators for more readability

    Hamcrest vs TestNG
    readability
    descriptive error messages
    type safety
    **/


    @Test
    public void extract_single_value_from_response() {
        given().
                baseUri("https://simple-grocery-store-api.glitch.me").
                when().
                get("/products").
                then().
                assertThat().
                statusCode(200).
//                body("category", hasItems("dairy", "coffee", "fresh-produce"));
//                body("category", contains("dairy", "coffee", "fresh-produce"));
//                body("category", empty());
//                body("category", is(not(emptyArray())));
//                body("category", hasSize(20));
//                body("category", everyItem(startsWith("Category:")));
//                body("[0]", hasKey("category"));
//                body("[0]", hasValue("coffee"));
//                body("[0]", hasEntry("category", "coffee"));
//                body("[0]", not(equalTo(Collections.EMPTY_MAP)));
//                body("category", anyOf(startsWith("cof"), containsString("coffee")));
        body("[0].category", equalTo("coffee"));
    }


}