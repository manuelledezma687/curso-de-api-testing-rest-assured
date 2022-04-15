import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;

abstract class BaseTest {

    @BeforeClass
    public static void setup(){

        RestAssured.baseURI= "https://reqres.in";
        RestAssured.basePath= "/api";
        RestAssured.filters( new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }
}
