package rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class HeadersHandling {

    @Test
    public void include_headers() {

        Header header = new Header("headerName", "value2");
        Header matchHeader = new Header("x-mock-match-request-headers", "headerName");

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
        Header matchHeader = new Header("x-mock-match-request-headers", "headerName");
        Headers headers = new Headers(header, matchHeader);

        given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                headers(headers).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void include_headers_as_map() {

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("headerName", "value2");
        headerMap.put("x-mock-match-request-headers", "headerName");

        given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                headers(headerMap).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void include_multi_value_headers() {

        Header header1 = new Header("multiValueHeader", "value1");
        Header header2 = new Header("multiValueHeader", "value2");
        Headers headers = new Headers(header1, header2);

        given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                headers(headers).
                log().headers().
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void include_multi_value_headers_validation() {

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("headerName", "value2");
        headerMap.put("x-mock-match-request-headers", "headerName");

        given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                headers(headerMap).
                when().
                get("/get").
                then().
                log().
                all().
                assertThat().
                statusCode(200).
                header("responseHeader","resValue2");
    }

    @Test
    public void extract_response_headers() {

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("headerName", "value2");
        headerMap.put("x-mock-match-request-headers", "headerName");

        Headers responseHeaders = given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                headers(headerMap).
                when().
                get("/get").
                then().
                extract().headers();

        for(Header header:responseHeaders){
            System.out.println("header name = " + header.getName() + ", ");
            System.out.println("header value = " + header.getValue());
        }

        System.out.println(responseHeaders.get("responseHeader"));
        System.out.println(responseHeaders.get("responseHeader").getName());
        System.out.println(responseHeaders.get("responseHeader").getValue());
        System.out.println(responseHeaders.getValue("responseHeader"));

    }

    @Test
    public void extract_multivalue_response_headers() {

        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("headerName", "value1");
        headerMap.put("x-mock-match-request-headers", "headerName");

        Headers responseHeaders = given().
                baseUri("https://2a9e572e-8410-4122-ae49-d3a51e84b550.mock.pstmn.io").
                headers(headerMap).
                when().
                get("/get").
                then().
                extract().headers();

        List<String> values = responseHeaders.getValues(("multiValueHeader"));
        System.out.println(values);

        for(String value:values) {
            System.out.println(value);
        }
    }
}
