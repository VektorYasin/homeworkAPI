package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class PetstoreSwaggerUrl {

    protected RequestSpecification spec;
    @Before
    public void setSpec(){
        spec=new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2/").build();

    }
}
