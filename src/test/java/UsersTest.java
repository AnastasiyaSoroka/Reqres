import io.restassured.response.ValidatableResponse;
import models.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static io.restassured.RestAssured.given;

public class UsersTest extends BaseTest {

    UserRequest user = UserRequest
            .builder()
            .job("QA")
            .name("AAA")
            .build();

    @Test
    public void postUser() {
        ValidatableResponse response = given()
                .body(user)
                .log().all()
                .when().post(String.format(URL, EndPoints.usersPost))
                .then()
                .log().all()
                .statusCode(201);

        String createdAt = response.extract().body().as(UserPostRequest.class).getCreatedAt();
        String expectedTime = LocalDateTime.now(Clock.systemUTC()).truncatedTo(ChronoUnit.MINUTES).toString();
        String actualTime = createdAt.substring(0, createdAt.length() - 8);
        Assert.assertEquals(actualTime, expectedTime, "createAt value is not correct");

        String userId = response.extract().body().as(UserPostRequest.class).getId();
        Assert.assertFalse(userId.isEmpty(), "User id shouldn't be empty");
    }

    @Test
    public void putUser() {
        ValidatableResponse response = given()
                .pathParam("id", "2")
                .body(user)
                .log().all()
                .when()
                .put(String.format(URL, EndPoints.usersGet))
                .then()
                .log().all()
                .statusCode(200);

        String updatedAt = response.extract().body().as(UserPutRequest.class).getUpdatedAt();
        String expectedTime = LocalDateTime.now(Clock.systemUTC()).truncatedTo(ChronoUnit.MINUTES).toString();
        String actualTime = updatedAt.substring(0, updatedAt.length() - 8);
        Assert.assertEquals(actualTime, expectedTime, "updateAt value is not correct");
    }

    @Test
    public void patchUser() {
        ValidatableResponse response = given()
                .pathParam("id", "2")
                .body(user)
                .log().all()
                .when()
                .patch(String.format(URL, EndPoints.usersGet))
                .then()
                .log().all()
                .statusCode(200);

        String updatedAt = response.extract().body().as(UserPutRequest.class).getUpdatedAt();
        String expectedTime = LocalDateTime.now(Clock.systemUTC()).truncatedTo(ChronoUnit.MINUTES).toString();
        String actualTime = updatedAt.substring(0, updatedAt.length() - 8);
        Assert.assertEquals(actualTime, expectedTime, "updateAt value is not correct");
    }

    @Test
    public void deleteUser() {
        ValidatableResponse response = given()
                .pathParam("id", "2")
                .log().all()
                .when()
                .delete(String.format(URL, EndPoints.usersGet))
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void getListUser() {
        ValidatableResponse response = given()
                .pathParam("id", "2")
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.usersPageGet))
                .then()
                .log().all()
                .statusCode(200);

        int pageNumberActual = response.extract().body().as(UsersListResponse.class).getPage();
        Assert.assertEquals(pageNumberActual, 2, "Page is not correct ");
    }

    @Test
    public void getSingleUser() {
        ValidatableResponse response = given()
                .pathParam("id", "2")
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.usersGet))
                .then()
                .log().all()
                .statusCode(200);

        int userIdActual = response.extract().body().as(UserSingleResponse.class).getData().getId();
        Assert.assertEquals(userIdActual, 2, "Id is not correct ");
    }

    @Test
    public void getListUserWithDelay() {
        ValidatableResponse response = given()
                .pathParam("id", "3")
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.usersDelayGet))
                .then()
                .log().all()
                .statusCode(200);

        String firstNameActual = response.extract().body().as(UsersListResponse.class).getData().get(0).getFirstName();
        Assert.assertEquals(firstNameActual, "George", "Name is not correct ");
    }

    @Test
    public void getSingleUserNotFound() {
        ValidatableResponse response = given()
                .pathParam("id", "25")
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.usersGet))
                .then()
                .log().all()
                .statusCode(404);
    }

}

