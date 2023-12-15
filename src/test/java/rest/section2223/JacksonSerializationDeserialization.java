package rest.section2223;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JacksonSerializationDeserialization {

    @Test
    public void jacksonSerialization() throws JsonProcessingException {
        HashMap<String,String> map = new HashMap<>();
        map.put("name","morpheus");
        map.put("job","leader");

        ObjectMapper objMapper = new ObjectMapper();
        String mapStr = objMapper.writeValueAsString(map);

        given().
                baseUri("https://reqres.in/api").
                body(mapStr).
                when().
                put("/users/2").
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void jacksonComplexSerialization() throws JsonProcessingException {

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string Photo One");

        HashMap<String, Object> categories = new HashMap<>();
        categories.put("id", 1);
        categories.put("name","string");

        HashMap<String, Object> tagsMap = new HashMap<>();
        tagsMap.put("id", 1);
        tagsMap.put("name","string");

        List<Object> tagsArray = new ArrayList<>();
        tagsArray.add(tagsMap);

        HashMap<String, Object> mainMap = new HashMap<>();
        mainMap.put("id", 1);
        mainMap.put("category", categories);
        mainMap.put("name", "doggo");
        mainMap.put("photoUrls", photoUrls);
        mainMap.put("tags", tagsArray);
        mainMap.put("status", "available");

        ObjectMapper objMapper = new ObjectMapper();
        String jsonObj = objMapper.writeValueAsString(mainMap);

        given().
                contentType(ContentType.JSON).
                body(jsonObj).
                log().all().
                when().
                post("https://petstore.swagger.io/v2/pet").
                then().
                log().all().
                assertThat().
                body("name", equalTo("doggo"));
    }

    @Test
    public void jacksonObjectNodeSerialization() throws JsonProcessingException {

        ObjectMapper objMapper = new ObjectMapper();
        ObjectNode objNode = objMapper.createObjectNode();

        objNode.put("name","morpheus");
        objNode.put("job","leader");

        String nodeStr = objMapper.writeValueAsString(objNode);

        given().
                baseUri("https://reqres.in/api").
//                body(nodeStr).
                body(objNode).
        when().
                put("/users/2").
        then().
                assertThat().
                statusCode(200);
    }


    @Test
    public void jacksonArrayNodeSerialization() {

        ObjectMapper objMapper = new ObjectMapper();

        ArrayNode photoArrNode = objMapper.createArrayNode();
        photoArrNode.add("string Photo One");

        ObjectNode categObjNode = objMapper.createObjectNode();
        categObjNode.put("id", 1);
        categObjNode.put("name","string");

        ObjectNode tagsObjNode = objMapper.createObjectNode();
        tagsObjNode.put("id", 1);
        tagsObjNode.put("name","string");

        ArrayNode tagsArrNode = objMapper.createArrayNode();
        tagsArrNode.add(tagsObjNode);

        ObjectNode mainObjNode = objMapper.createObjectNode();

        mainObjNode.put("id", 1);
        mainObjNode.set("category", categObjNode);
        mainObjNode.put("name", "doggie");
        mainObjNode.set("photoUrls", photoArrNode);
        mainObjNode.set("tags", tagsArrNode);
        mainObjNode.put("status", "available");

        given().
                contentType(ContentType.JSON).
                body(mainObjNode).
                when().
                post("https://petstore.swagger.io/v2/pet").
                then().
                assertThat().
                body("name", equalTo("doggie"));
    }
}
