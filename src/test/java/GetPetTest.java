import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetPetTest {

    @Test
    public void getPetById() {
        int id = 1;
        given()
                .log().all()
                .baseUri("https://petstore.swagger.io")
                .when()
                .get ("/v2/pet/{id}", id)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void createPet() {

    }

}
