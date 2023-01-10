package homeworks;

import baseUrl.ReqresIn;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Homework6 extends ReqresIn {
            //Homework6:
   /*
       Given
              https://reqres.in/api/unknown/
       When
            I send GET Request to the URL
       Then
            1)Status code is 200
            2)Print all pantone_values
            3)Print all ids greater than 3 on the console
              Assert that there are 3 ids greater than 3
            4)Print all names whose ids are less than 3 on the console
              Assert that the number of names whose ids are less than 3 is 2
    */

@Test
    public void homework6() {
    //set the Url
    spec.pathParams("first","unknown");

    //set the expected data

    //send the request and get the response
    Response response= given().spec(spec).when().get("/{first}");
    response.prettyPrint();

    //do assertions
    // 1)Status code is 200
    response.then().assertThat().statusCode(200);

    //2)Print all pantone_values
    JsonPath jsonPath=response.jsonPath();//jsonPath objesi yarat,
    List<String> pantone_values=jsonPath.getList("data.pantone_value");
    System.out.println("pantone_values = " + pantone_values);

    //3)Print all ids greater than 3 on the console
    List<Integer> ids = jsonPath.getList("data.findAll{it.id>3}.id");//Groovy
    System.out.println("ids = " + ids);//[4, 5, 6]

    // Assert that there are 3 ids greater than 3
    Assert.assertEquals(3, ids.size());

    List<Integer> ids1=jsonPath.getList("data.id");
    List<Integer> idsGreaterThan3= new ArrayList<>();
    for(int w:ids1){
        if(w>3){
            idsGreaterThan3.add(w);
        }
        System.out.println("idsGreaterThan3 = " + idsGreaterThan3);
    }

    //2nd Way: Recommended
    List<Integer> idsGraterThan3Lambda = ids.stream().filter(t->t>3).collect(Collectors.toList());
    System.out.println(idsGraterThan3Lambda.size());
    Assert.assertEquals(3,idsGraterThan3Lambda.size());

    //4)Print all names whose ids are less than 3 on the console
    List<String> namesIdsLessThan3Groovy= jsonPath.getList("data.findAll{it.id<3}.name");
    System.out.println("names whose ids are less than 3 = " + namesIdsLessThan3Groovy);

    //Assert that the number of names whose ids are less than 3 is 2
    Assert.assertEquals(2,namesIdsLessThan3Groovy.size());

    namesIdsLessThan3Groovy.stream().forEach(System.out::println);

    }
}





