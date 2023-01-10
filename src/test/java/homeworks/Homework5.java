package homeworks;

import baseUrl.ReqresIn;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Homework5 extends ReqresIn {
   /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void homework5(){
        //set the url
        spec.pathParams("first", "unknown", "second", 3);

        //set the expected data

        //send the request and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //do assertion
        //1st Way:
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id",equalTo(3),
                    "data.name", equalTo("true red"),
                    "data.year", equalTo(2002),
                    "data.color", equalTo("#BF1932"),
                    "data.pantone_value", equalTo("19-1664"),
                    "support.url", equalTo("https://reqres.in/#support-heading"),
                    "support.text", equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));

        //2nd way  We will use JsonPath Class
        JsonPath jsonPath = response.jsonPath();

        //hard assertion
        assertEquals(3, jsonPath.getInt("data.id"));
        assertEquals("true red", jsonPath.getString("data.name"));
        assertEquals(2002, jsonPath.getInt("data.year"));
        assertEquals("#BF1932", jsonPath.getString("data.color"));
        assertEquals("19-1664", jsonPath.getString("data.pantone_value"));
        assertEquals("https://reqres.in/#support-heading", jsonPath.getString("support.url"));
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", jsonPath.getString("support.text"));

        //Soft Assertion
        //1st: Create SoftAssert Object
        SoftAssert softAssert = new SoftAssert();

        //2nd: Do Assertion
        softAssert.assertEquals(jsonPath.getInt("data.id"),3, "id did not match");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red", "name did not match");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002, "year did not match");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932", "color did not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664", "pantone_value did not match");
        softAssert.assertEquals(jsonPath.getString("support.url"),"https://reqres.in/#support-heading", "url did not match");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!", "text did not match");

        //3rd: Use assetAll() method.
        //We have to assetAll() method.
        // SoftAssert don't throw an exception when an assert fails, but it records the failure.
        // The test execution will continue with the next step after the assert statement.
        // Calling assertAll() will cause an exception to be thrown if at least one assertion failed.
        softAssert.assertAll();

    }
}
