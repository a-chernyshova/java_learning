package learn.poc.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class SimpleGetTest {
    public static final String URL = System.getProperty("url");

    @BeforeAll
    public static void init() {
        baseURI = URL;
        port = 8085;
    }

    @DisplayName("Simple get test")
    @Test
    void firstTest(){
        RequestSpecification requestSpec = RestAssured.given();
        Response response = requestSpec.get(URL);
        response.prettyPrint();
        System.out.println(response.statusCode());
    }

    @DisplayName("Simple formatted get test")
    @Test
    void secondTest(){
        RestAssured.given()
                .get()
                .then()
                .statusCode(200);

        RestAssured.given()
                .expect()
                .statusCode(200)
                .when()
                .get();

        given()
                .expect()
                .statusCode(200)
                .when()
                .get();
    }

    @DisplayName("Get test with query params")
    @Test
    void thirdTest(){
        Response response = given()
                .queryParams( "programme", "Computer Science", "limit", 2)
                .when()
                .get("student/list");
        System.out.println(response.statusCode());
        response.prettyPrint();

        Map<String,Object> params = new HashMap<>();
        params.put("programme", "Computer Science");
        params.put("limit", 3);

        Response response2 = given()
                .queryParams(params)
                .when()
                .get("student/list");
        response2.prettyPrint();

    }

    @DisplayName("PathParam exmple test")
    @Test
    void fourthTest(){
        Response response = given().pathParam("id", 1).when().get("student/{id}");
        response.prettyPrint();
    }
}
