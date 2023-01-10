package homeworks;

import baseUrl.HerOkuApp;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Homework4 extends HerOkuApp {
/*
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
        When
            User sends get request to the URL
        Then
            Status code is 200
        And
            Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"
 */
    @Test
    public void homework4(){
        //        Given
        //        https://restful-booker.herokuapp.com/booking?firstname=Brandon&lastname=Wilson
      spec.pathParams("first","booking").queryParams("firstname", "Brandon","lastname", "Wilson");

        //When User sends get request to the URL
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //        Then
        //        Status code is 200
        Assert.assertEquals(200,response.getStatusCode());

        //        And
        //        Among the data there should be someone whose firstname is "Brandon" and lastname is "Wilson"
        Assert.assertTrue(response.asString().contains("bookingid"));

    }
}
