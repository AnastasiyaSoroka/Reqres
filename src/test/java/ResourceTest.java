import io.restassured.response.ValidatableResponse;
import models.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResourceTest extends BaseTest {

    @Test
    public void getSingleResource() {
        ValidatableResponse response = given()
                .pathParam("id", "2")
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.resourceSingleGet))
                .then()
                .log().all()
                .statusCode(200);

        int resourceIdActual = response.extract().body().as(ResourceSingleResponse.class).getData().getId();
        Assert.assertEquals(resourceIdActual, 2, "Id is not correct ");
    }

    @Test
    public void getSingleResourceNotFound() {
        ValidatableResponse response = given()
                .pathParam("id", "23")
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.resourceSingleGet))
                .then()
                .log().all()
                .statusCode(404);
    }

    @Test
    public void getResourceList() {
        ValidatableResponse response = given()
                .log().all()
                .when()
                .get(String.format(URL, EndPoints.resourceListGet))
                .then()
                .log().all()
                .statusCode(200);

        int pageNumberActual = response.extract().body().as(ResourceListResponse.class).getPage();
        Assert.assertEquals(pageNumberActual, 1, "Page is not correct ");

    }

}
