package homeworks;

import baseUrl.ReqresIn;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework2 extends ReqresIn {
    /*
        Given
            https://reqres.in/api/users/23
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Server is "cloudflare"
        And
            Response body should be empty
     */

    @Test
    public void homework2() {
        //set the url
        spec.pathParams("first","users","second",23);

        //set the expected data

        //send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
        assertEquals("cloudflare",response.getHeader("Server"));
        assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]", "").length());
        //or
        assertEquals(0,response.as(HashMap.class).size());

    }

}


