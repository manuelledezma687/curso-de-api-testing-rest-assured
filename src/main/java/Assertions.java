import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;

public class Assertions{

    @Test
    public void loginTest(){

      RestAssured
                .given()
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

        RestAssured
                .given()
                .contentType(ContentType.JSON)
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id",equalTo(2));


    }

}
