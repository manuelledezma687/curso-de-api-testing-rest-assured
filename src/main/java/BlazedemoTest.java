import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BlazedemoTest {

    @Before
    public void setup (){

        RestAssured.baseURI= "https://blazedemo.com/";
        RestAssured.filters( new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void buyATicket(){
                given()
                .when()
                        .body("{\\n\" +\n" +
                                "                        \"    \\\"id\\\": \\\"1648840628654\\\",\\n\" +\n" +
                                "                        \"    \\\"status\\\": \\\"PendingCapture\\\",\\n\" +\n" +
                                "                        \"    \\\"amount\\\": \\\"555\\\",\\n\" +\n" +
                                "                        \"    \\\"currency\\\": \\\"USD\\\",\\n\" +\n" +
                                "                        \"    \\\"authCode\\\": \\\"888888\\\",\\n\" +\n" +
                                "                        \"    \\\"payment\\\": {\\n\" +\n" +
                                "                        \"        \\\"cardNumber\\\": \\\"xxxxxxxxxxxx1111\\\",\\n\" +\n" +
                                "                        \"        \\\"cardExpirationMonth\\\": \\\"11\\\",\\n\" +\n" +
                                "                        \"        \\\"cardExpirationYear\\\": \\\"2018\\\"\\n\" +\n" +
                                "                        \"    }")
                .post("/confirmation.php")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }



}
