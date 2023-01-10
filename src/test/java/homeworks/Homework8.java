package homeworks;

import baseUrl.PetstoreSwaggerUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework8 extends PetstoreSwaggerUrl {
   /*
    Type automation code to create a 'user' by using "https://petstore.swagger.io/"" documantation.

        Given
            1) https://petstore.swagger.io/v2/user
            2)
                            {
                              "id": 0,
                              "username": "yaso",
                              "firstName": "Yasin",
                              "lastName": "Aytekin",
                              "email": "yaytekin@yahoo.com",
                              "password": "1234",
                              "phone": "1234567",
                              "userStatus": 0
                            }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }
     */
    @Test
    public void homework8(){
        //set the url
        spec.pathParams("first","user");

        //Set the expected data
        Map<String,Object> expectedData = new HashMap<>();//You can create payload by using pojo class as well.
        expectedData.put("username","yaso");
        expectedData.put("firstName","Yasin");
        expectedData.put("lastName","Aytekin");
        expectedData.put("email","yaytekin@yahoo.com");
        expectedData.put("password","1234");
        expectedData.put("phone","1234567");
        expectedData.put("userStatus",0);
        System.out.println("expectedData = " + expectedData);

        //send the post and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();


        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(200,response.statusCode());
        assertEquals(200,actualData.get("code"));
        assertEquals("unknown",actualData.get("type"));
    }
}


