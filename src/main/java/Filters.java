import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class Filters{

    @Before
    public void setup (){

        RestAssured.baseURI= "https://reqres.in";
        RestAssured.basePath= "/api";
        RestAssured.filters( new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
    @Test
    public void loginTest(){

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token",notNullValue());


    }


    @Test
    public void getSingleUserTest(){

        given()
                .contentType(ContentType.JSON)
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id",equalTo(2));


    }

}

