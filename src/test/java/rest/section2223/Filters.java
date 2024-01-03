package rest.section2223;

import org.junit.Before;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static io.restassured.RestAssured.*;

public class Filters {

    @Test
    public void filtersTest() {
        given().
                baseUri("https://postman-echo.com/").
                param("foo1","bar1").
//                log().all().
                filter(new RequestLoggingFilter(LogDetail.BODY)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS)).
        when().
                get("/get").
        then().
//                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void printStreamFiltersTest() throws FileNotFoundException {

        PrintStream FileOutputStream = new PrintStream(new File("restAssured.log"));

        given().
                baseUri("https://postman-echo.com/").
                param("foo1","bar1").
                filter(new RequestLoggingFilter(FileOutputStream)).
                filter(new ResponseLoggingFilter(FileOutputStream)).
        when().
                get("/get").
        then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void printStreamWithDetailsFiltersTest() throws FileNotFoundException {

        PrintStream FileOutputStream = new PrintStream(new File("restAssured.log"));

        given().
                baseUri("https://postman-echo.com/").
                param("foo1","bar1").
                filter(new RequestLoggingFilter(LogDetail.BODY,FileOutputStream)).
                filter(new ResponseLoggingFilter(LogDetail.STATUS,FileOutputStream)).
                when().
                get("/get").
                then().
                assertThat().
                statusCode(200);
    }


    RequestSpecification request;
    ResponseSpecification response;

    @Before
    public void before() throws FileNotFoundException {

        PrintStream FileOutputStream = new PrintStream(new File("restAssured.log"));

        io.restassured.builder.RequestSpecBuilder requestSpecBuilder = new io.restassured.builder.RequestSpecBuilder().
                addFilter(new RequestLoggingFilter(LogDetail.BODY,FileOutputStream)).
                addFilter(new RequestLoggingFilter(LogDetail.HEADERS,FileOutputStream)).
                addFilter(new ResponseLoggingFilter(LogDetail.STATUS,FileOutputStream));
        request = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        response = responseSpecBuilder.build();
    }


    @Test
    public void reuseFiltersTest() {
        given(request).
                baseUri("https://postman-echo.com/").
                param("foo1","bar1").
        when().
                get("/get").
        then().spec(response).
                assertThat().
                statusCode(200);
    }
}
