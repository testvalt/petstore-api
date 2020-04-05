import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PetTests {

    int id = 182;

    String body = "{\n" +
            "  \"id\": \""+ id +"\",\n" +
            "  \"category\": {\n" +
            "    \"id\": 0,\n" +
            "    \"name\": \"string\"\n" +
            "  },\n" +
            "  \"name\": \"Snoopy\",\n" +
            "  \"photoUrls\": [\n" +
            "    \"string\"\n" +
            "  ],\n" +
            "  \"tags\": [\n" +
            "    {\n" +
            "      \"id\": 182,\n" +
            "      \"name\": \"Snoopy\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"status\": \"available\"\n" +
            "}";

    //Response response = given().get("/pet/{id}", id);

    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }

    @After
    public void after() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 200)
            deletePetById();
    }

    @Test
    public void getPetById() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 404)
            createNewPet();
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body( "id", anyOf(is(id), is("available")))
                .statusCode(200);
    }

    @Test
    public void getPetByStatus() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 404)
            createNewPet();
        String status = "available";
        given()
                .log()
                .all()
                .when()
                .param("status", status)
                .get("/pet/findByStatus")
                .then()
                .log()
                .all()
                .body("status", everyItem(equalTo(status)))
                .statusCode(200);
    }

    @Test
    public void createNewPet() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 200)
            deletePetById();
        given()
                .log()
                .all()
                .body(body)
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body( "id", is(id))
                .statusCode(200);
    }

    @Test
    public void updatePetByDataForm() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 404)
            createNewPet();
        given()
                .log()
                .all()
                .contentType("application/x-www-form-urlencoded")
                .params("name", "Sezam", "status", "pending")
                .when()
                .post("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(id)))
                .statusCode(200);
    }

    @Test
    public void updatePet() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 404)
            createNewPet();
        given()
                .log()
                .all()
                .body(body)
                .when()
                .put("/pet")
                .then()
                .log()
                .all()
                .body( "name", is("Snoopy"))
                .statusCode(200);
    }

    @Test
    public void deletePetById() {
        Response response = given().get("/pet/{id}", id);
        if (response.getStatusCode() == 404)
            createNewPet();
        if (response.getStatusCode() == 200)
            System.out.println(id + "pet exists");
        given()
                .log()
                .all()
                .when()
                .delete("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(id)))
                .statusCode(200);
    }

}
