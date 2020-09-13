package learn.poc.tests;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class JsonPathJayWayTests {

    static String jsonResponse;

    static void print(String val) {
        System.out.println("------------------------");
        System.out.println(val);
        System.out.println("------------------------");
    }

    @BeforeAll
    public static void initialize() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        jsonResponse = given().when().get("/products").asString();
    }

    @BeforeEach
    void printToConsole() {
        System.out.println("---Start test method---");
    }

    @AfterEach
    void printToConsoleEnd() {
        System.out.println("---End test method---");
    }

    @DisplayName("Get root element")
    @Test
    public void getRoot() {
        Map<String, ?> rootElement = JsonPath.read(jsonResponse, "$");
        print(rootElement.toString());
    }

    @DisplayName("Get the total amount")
    @Test
    public void getTotalFromResponse(){
        int totalValue = JsonPath.read(jsonResponse, "$.total");
        print(totalValue + "");
    }

    @DisplayName("Get all data element")
    @Test
    public void getAllDataList(){
        List<HashMap<String, Object>> dataElements = JsonPath.read(jsonResponse, "$.data");
        dataElements.forEach(System.out::println);
    }

    @DisplayName("Get firstDataElement")
    @Test
    public void getFirstDataElement(){
        Map<String, ?> firstElement = JsonPath.read(jsonResponse, "$.data[0]");
        print(firstElement.toString());
    }

    @DisplayName("Get lastDataElement")
    @Test
    public void getLastDataElement(){
        Map<String, ?> lastElement = JsonPath.read(jsonResponse, "$.data[-1]");
        print(lastElement.toString());
    }

    @DisplayName("Get all IDs")
    @Test
    public void getAllIds() {
        List<String> ids = JsonPath.read(jsonResponse, "$.data[*].id");
        print(ids.toString());
    }

    @DisplayName("Get any ids from all nested elements")
    @Test
    public void getAnyIds(){
        List<String> allIds = JsonPath.read(jsonResponse, "$..[*].id");
        print(allIds.toString());
    }

    @DisplayName("Filter out results")
    @Test
    public void filterOutResults(){
        List<String> products = JsonPath.read(jsonResponse, "$.data[?(@.price<6)].name");
        print(products.toString());
        products.forEach(System.out::println);
    }
}
