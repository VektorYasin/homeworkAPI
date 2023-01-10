package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresIn {
        protected RequestSpecification spec;

        //If you put @Before annotation at the top of a method,
        // it will be executed before other test methods.
        @Before
        public void setUp(){

           spec = new RequestSpecBuilder().setBaseUri("https://reqres.in/api/").build();

        }
    }


