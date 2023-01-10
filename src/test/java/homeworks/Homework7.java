package homeworks;

import baseUrl.ReqresIn;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import testData.ReqresInTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework7 extends ReqresIn {
  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
        @Test
        public void homework7a(){
            //set the url
            spec.pathParams("first", "user");

            //Set the expected data
            ReqresInTestData obj = new ReqresInTestData();
            Map<String, Object> expectedData = ReqresInTestData.reqresUserSetUp("morpheus","leader");
            System.out.println("expectedData = " + expectedData);

            Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
            response.prettyPrint();

            Map<String,String> actualData = response.as(HashMap.class);
            System.out.println("actualData = " + actualData);

            Assert.assertEquals(201, response.getStatusCode());
            Assert.assertEquals(expectedData.get("name"),actualData.get("name"));
            Assert.assertEquals(expectedData.get("job"),actualData.get("job"));
        }
    @Test
    public void homework7() {
        //set the url
        spec.pathParams("first", "user");

        //set the expected data
        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("name", "morpheus");
        expectedData.put("job", "leader");
        System.out.println("expectedData = " + expectedData);

        //send the post and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //do assertion

        Map<String, String> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(201, response.statusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));
    }
}
