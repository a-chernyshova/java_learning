package learn.poc.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SimpleDeleteTest {
    public static final String URL = System.getProperty("url");
    public static final String id = System.getProperty("id");

    @BeforeAll
    public static void init() {
        baseURI = URL;
        port = 8085;
    }

    @DisplayName("Simple Delete request")
    @Test
    void firstTest(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .when()
                .delete("student/" + id)
                .then()
                .statusCode(204);

        given()
                .when()
                .get("student/" + id)
                .then()
                .statusCode(404);
    }
}
