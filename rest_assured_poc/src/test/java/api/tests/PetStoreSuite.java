package api.tests;

import api.utils.ReadConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static api.utils.ApiUrls.*;
import static org.apache.http.HttpStatus.*;

public class PetStoreSuite {
    protected final ReadConfiguration configReader = new ReadConfiguration();
    String findByStatus = "pet/findByStatus?status=%s";
    String findById = "pet/%s";
    private static Logger log = LoggerFactory.getLogger(PetStoreSuite.class);

    @Test(priority=1, description = "get pet by status", dataProvider = "validStatuses")
    public void verifyGettingListByStatus(String status) {
        Response resp = RestAssured.given()
                .log()
                .uri()
                .when()
                .get(baseUrl + String.format(findByStatus, status))
                .andReturn();

        Assert.assertEquals(resp.statusCode(), SC_OK);
        Assert.assertTrue(resp.timeIn(TimeUnit.SECONDS) < 2, "Response should be less than in 1 sec");
        Assert.assertTrue(resp.body().asString().contains(status));
    }

    @Test(priority=1, description = "get pet by invalid status")
    public void verifyGetByInvalidId() {
        RestAssured.given()
                .log()
                .uri()
                .when()
                .get(baseUrl + String.format(findById, "abc"))
                .then()
                .assertThat()
                .statusCode(SC_NOT_FOUND);
    }

    @Test(priority=1, description = "get pet by valid status")
    public void verifyGetByValidId() {
        RestAssured.given()
                .log()
                .uri()
                .when()
                .get(baseUrl + String.format(findById, "1"))
                .then()
                .assertThat()
                .statusCode(SC_OK)
        .extract().asString().isEmpty();
    }

    @Test(priority = 2, description = "post new pet")
    public void postNewTest() {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", 1);
        requestParams.put("category", "{ \"id\": 0, \"name\": \"string\" }");
        requestParams.put("name", "doggie");
        requestParams.put("photoUrls", "[ \"string\" ], \"tags\": [ { \"id\": 0, \"name\": \"string\" } ]");
        requestParams.put("status", "available");
        RestAssured.given()
                .header("accept", "application/json")
                .log()
                .uri()
                .when()
                .body(requestParams.toJSONString())
                .post(baseUrl + "/v2/pet")
                .then()
                .assertThat()
                .statusCode(SC_OK);


        Assert.assertTrue("doggie".equals(RestAssured
                .given()
                .log()
                .uri()
                .when()
                .get(baseUrl + String.format(findById, "1"))
                .body()
                .jsonPath()
                .getString("name")));
    }

    @Test(priority = 3, description = "delete the pet", dependsOnMethods = "postNewTest")
    public void deleteTest() {
        RestAssured.given()
                .log()
                .uri()
                .when()
                .delete(baseUrl + petEndpoint + "/1")
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.get(baseUrl + petEndpoint + "/1")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test(priority = 3, description = "put the pet")
    public void updateTest() {
        String requestBody = "{\"id\":1,\"category\":{\"id\":0,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"sold\"}";
        RestAssured.given()
                .log()
                .uri()
                .when()
                .header("accept", "application/json")
                .body(requestBody)
                .put(baseUrl + petEndpoint)
                .then()
                .assertThat()
                .statusCode(200);

        RestAssured.get(baseUrl + petEndpoint + "/1")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test(priority = 4, description = "log in by a user")
    public void logInTest() {
        Response resp = RestAssured.given()
                .log()
                .uri()
                .when()
                .get(baseUrl + String.format(userLogin, configReader.getUserName(), configReader.getUserPass()))
                .andReturn();

        Assert.assertEquals(resp.statusCode(), SC_OK);
        Assert.assertFalse(resp.header("x-expires-after").isEmpty());
        Assert.assertTrue(resp.timeIn(TimeUnit.SECONDS) < 2);
    }

    @DataProvider
    public Object[][] validStatuses() {
        return new Object[][]{
                {"available"},
                {"sold"},
                {"pending"}
        };
    }
}
