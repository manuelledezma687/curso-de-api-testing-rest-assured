import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OthersMethods{

    @Before
    public void setup (){

        RestAssured.baseURI= "https://reqres.in";
        RestAssured.basePath= "/api";
        RestAssured.filters( new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
    @Test
    public void deleteUserTest(){

        given()
                .delete("users/2")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);

    }

    @Test
    public void patchUserTest(){
        String nameUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .patch("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name",equalTo("morpheus"))
                .extract()
                .jsonPath().getString("name");
        assertThat(nameUpdated, equalTo("morpheus"));
    }

    @Test
    public void putUserTest(){
        String JobUpdated = given()
                .when()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .put("users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("name",equalTo("morpheus"))
                .extract()
                .jsonPath().getString("job");
        assertThat(JobUpdated, equalTo("zion resident"));
    }

    /* En este test vamos a explorar las extracciones de un responsse */
    @Test
    public void getAllUsersTest() {
        Response response = given()
                .get("users?page=2");

        Headers headers = response.getHeaders();
        int statusCode = response.getStatusCode();
        String body = response.getBody().asString();
        String contentType = response.getContentType();

        assertThat(statusCode, equalTo(HttpStatus.SC_OK));
        System.out.println("body" + body);
        System.out.println("content type" + contentType);
        System.out.println("headers" + headers.toString());
    }


    @Test
    public void getAllUsersTest2() {
        String response = given()
                .when()
                .get("users?page=2").then().extract().body().asString();

        int page = from(response).get("page");
        int totalPages = from(response).get("total_pages");
        int idFirstUser = from(response).get("data[0].id");


        System.out.println("page: " + page);
        System.out.println("total pages: " + totalPages);
        System.out.println("id first user: " + idFirstUser);


        List <Map> userWithIdGreaterThan10 = from(response).get("data.findAll { user -> user.id > 10}");
        String email = userWithIdGreaterThan10.get(0).get("email").toString();
        List <Map> user= from(response).get("data.findAll { user -> user.id > 10 && user.last_name == 'Howell'}");
        int id = Integer.valueOf( user.get(0).get("id").toString());

    }
}