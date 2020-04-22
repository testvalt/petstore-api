import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class PetEndpoint {


    private final static String CREATE_PET = "/pet";
    private final static String GET_PET_BY_ID = "/pet/{id}";
    private final static String GET_PET_BY_STATUS = "/pet/findByStatus?status={status}";
    private final static String UPDATE_PET = "/pet";
    private final static String UPDATE_PET_BY_DATAFORM = "/pet/{id}";
    private final static String UPLOAD_PET_IMAGE = "/pet/{petId}/uploadImage";
    private final static String DELETE_PET_BY_ID = "/pet/{id}";

    static {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL));
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .log()
                .all();
    }

    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .statusCode(SC_OK);
    }

    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body("id", anyOf(is(petId), is(Status.AVAILABLE)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getPetByStatus(String status) {
        return given()
                .when()
                .get(GET_PET_BY_STATUS, status)
                .then()
                .body("status", everyItem(equalTo(status)))
                .statusCode(SC_OK);
    }

    public ValidatableResponse updatePet(Pet pet, long petId) {
        return given()
                .body(pet)
                .when()
                .put(UPDATE_PET)
                .then()
                .body("name", is("Snoopy"))
                .statusCode(SC_OK);

    }

    public ValidatableResponse updatePetByDataForm(long petId) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .params("name", "Sezam", "status", "pending")
                .when()
                .post(UPDATE_PET_BY_DATAFORM, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);

    }

    public ValidatableResponse uploadPetImage(long petId) {
        return given()
                .contentType("multipart/form-data")
                .multiPart(new File("/Users/val/Desktop/250px-Scooby-gang-1969.jpg"))
                .when()
                .post(UPLOAD_PET_IMAGE, petId)
                .then()
                .body("message", is("additionalMetadata: null\nFile uploaded to ./250px-Scooby-gang-1969.jpg, 20281 bytes"))
                .statusCode(SC_OK);
    }

    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }
}
