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

public class UpdateTest {
    public static final String URL = System.getProperty("url");

    @BeforeAll
    public static void init() {
        baseURI = URL;
        port = 8085;
    }

    @DisplayName("Simple Put request")
    @Test
    void firstTest(){
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
                .put("student/1")
                .then()
                .statusCode(200);
    }

    @DisplayName("Simple patch request")
    @Test
    void secondTest(){
        StudentPojo studentPojo = new StudentPojo();
        Faker faker = new Faker();
        studentPojo.setEmail(faker.internet().emailAddress());

        given()
                .when()
                .contentType(ContentType.JSON)
                .when()
                .body(studentPojo)
                .patch("student/1")
                .then()
                .statusCode(200);
    }
}
