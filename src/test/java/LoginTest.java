import io.restassured.response.ValidatableResponse;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {
    LoginRequest login;

    @Test
    public void postLoginSuccessTest() {
        login = LoginRequest
                .builder()
                .email("eve.holt@reqres.in")
                .password("pistol")
                .build();

        ValidatableResponse response = given()
                .contentType("application/json")
                .body(login)
                .log().all()
                .when().post(String.format(URL, EndPoints.loginPost))
                .then()
                .log().all()
                .statusCode(200);

        int id = response.extract().body().as(RegisterPostResponse.class).getId();
        String token = response.extract().body().as(RegisterPostResponse.class).getToken();

        Assert.assertFalse(String.valueOf(id).isEmpty(), "Id shouldn't be empty");
        Assert.assertFalse(token.isEmpty(), "Token shouldn't be empty");
    }

    @Test
    public void postLoginUnsuccessTest() {
        login = LoginRequest
                .builder()
                .email("sydney@fife")
                .build();

        ValidatableResponse response = given()
                .contentType("application/json")
                .body(login)
                .log().all()
                .when().post(String.format(URL, EndPoints.loginPost))
                .then()
                .log().all()
                .statusCode(400);

        String error = response.extract().body().as(ErrorMessage.class).getError();
        Assert.assertFalse(error.isEmpty(), "Error should be provided");

    }

}
