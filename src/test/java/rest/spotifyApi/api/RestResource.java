package rest.spotifyApi.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static rest.spotifyApi.api.Route.API;
import static rest.spotifyApi.api.Route.TOKEN;
import static rest.spotifyApi.api.SpecBuilder.*;

public class RestResource {


    public static Response post(String path, String token, Object requestBody) {
        System.out.println("inside rest");
        return given(getRequestSpec()).
                body(requestBody).
                header("Authorization", "Bearer " + token).
                when().
                post(path).
                then().spec(getResponseSpec()).
                contentType(ContentType.JSON).extract().response();
    }

    public static Response get(String path, String token) {
        return given(getRequestSpec()).
                header("Authorization", "Bearer " + token).
                when().
                get(path).
                then().spec(getResponseSpec()).
                contentType(ContentType.JSON).extract().response();
    }

    public static Response update(String path, String token, Object requestBody) {
        return given(getRequestSpec()).
                body(requestBody).
                header("Authorization", "Bearer " + token).
                when().
                put(path).
                then().spec(getResponseSpec()).extract().response();
    }

    public static Response postAccount(HashMap<String, String> formParams) {
        Response response = given(getAccountRequestSpec()).
                formParams(formParams).
                when().
                post(API + TOKEN).
                then().
                spec(getResponseSpec()).
                extract().response();

        return response;
    }

}
