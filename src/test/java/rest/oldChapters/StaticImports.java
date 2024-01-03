package rest.oldChapters;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StaticImports {

    @Before
    public void init() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";
    }


    @DisplayName("get product by id")
    @Test
    public void getProductByIdPrintRequestHeaders() {

        given()
                .when()
                .get("/products/4643")
                .then()
                .log()
                .all()
                .statusCode(200).
                body("name", is(equalTo("Starbucks Coffee Variety Pack, 100% Arabica")),
                        "manufacturer", is(equalTo("Starbucks")));
    }
}
