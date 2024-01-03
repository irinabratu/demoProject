package rest.oldChapters;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class NonStaticImports {

    @Before
    public void init() {
        RestAssured.baseURI = "https://simple-grocery-store-api.glitch.me";
    }


    @DisplayName("get product by id")
    @Test
    public void getProductByIdPrintRequestHeaders() {

        RestAssured.given()
                .when()
                .get("/products/4643")
                .then()
                .log()
                .all()
                .statusCode(200).
                body("name", Matchers.is(Matchers.equalTo("Starbucks Coffee Variety Pack, 100% Arabica")),
                        "manufacturer", Matchers.is(Matchers.equalTo("Starbucks")));
    }
}
