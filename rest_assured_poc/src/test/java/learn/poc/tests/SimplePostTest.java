package learn.poc.tests;

import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.studentapp.model.StudentPojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class SimplePostTest {
    public static final String URL = System.getProperty("url");

    @BeforeAll
    public static void init() {
        baseURI = URL;
        port = 8085;
    }

    @DisplayName("Simple POST request")
    @Test
    void firstTest(){

        String payload = "{ \"firstName\": \"TestName\", \"lastName\": \"TestSecName\", \"email\": \"qa1@test.net\", \"programme\": \"Quality Assurance\", \"courses\": [ \"Programming\", \"Computer Science\" ] }";

        given()
                .when()
                .contentType(ContentType.JSON)
                .when()
                .body(payload)
                .post("student")
                .then()
                .statusCode(201);

        given()
                .when()
                .contentType(ContentType.JSON)
                .when()
                .body(payload)
                .post("student")
                .then()
                .statusCode(500);
    }

    @DisplayName("Simple POST request using pojo obbject to create a payload")
    @Test
    void secondTest(){
        StudentPojo studentPojo = new StudentPojo();

        List<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("AWS");

        studentPojo.setFirstName("Test1");
        studentPojo.setLastName("Test1");
        studentPojo.setEmail("test_1@qa.at");
        studentPojo.setProgramme("Quality Assurance");
        studentPojo.setCourses(courses);

        given()
                .when()
                .contentType(ContentType.JSON)
                .when()
                .body(studentPojo)
                .post("student")
                .then()
                .statusCode(201);
    }

    @DisplayName("Simple POST request using fake library to create a payload")
    @Test
    void thirdTest(){
        StudentPojo studentPojo = new StudentPojo();
        Faker faker = new Faker();

        List<String> courses = new ArrayList<>();
        courses.add("Java");
        courses.add("AWS");

        studentPojo.setFirstName(faker.name().firstName());
        studentPojo.setLastName(faker.name().lastName());
        studentPojo.setEmail(faker.internet().emailAddress());

        studentPojo.setProgramme("Quality Assurance");
        studentPojo.setCourses(courses);

        given()
                .when()
                .contentType(ContentType.JSON)
                .when()
                .body(studentPojo)
                .post("student")
                .then()
                .statusCode(201);
    }
}
