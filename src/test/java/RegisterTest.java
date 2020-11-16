import io.restassured.response.ValidatableResponse;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterTest extends BaseTest {
    RegisterRequest register;

    @Test
    public void postRegisterSuccessTest() {
        register = RegisterRequest
                .builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        ValidatableResponse response = given()
                .contentType("application/json")
                .body(register)
                .log().all()
                .when().post(String.format(URL, EndPoints.registerPost))
                .then()
                .log().all()
                .statusCode(200);

        int id = response.extract().body().as(RegisterPostResponse.class).getId();
        String token = response.extract().body().as(RegisterPostResponse.class).getToken();

        Assert.assertFalse(String.valueOf(id).isEmpty(), "Id shouldn't be empty");
        Assert.assertFalse(token.isEmpty(), "Token shouldn't be empty");
    }

    @Test
    public void postRegisterUnsuccessTest() {
        register = RegisterRequest
                .builder()
                .email("sydney@fife")
                .build();

        ValidatableResponse response = given()
                .contentType("application/json")
                .body(register)
                .log().all()
                .when().post(String.format(URL, EndPoints.registerPost))
                .then()
                .log().all()
                .statusCode(400);

        String error = response.extract().body().as(ErrorMessage.class).getError();
        Assert.assertFalse(error.isEmpty(), "Error should be provided");

    }
}




