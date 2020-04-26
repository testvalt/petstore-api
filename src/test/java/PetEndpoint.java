import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.apache.commons.io.FileUtils;

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
                .body("name", is(pet.getName()))
                .statusCode(SC_OK);
    }

    public ValidatableResponse getPet(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body("id", is(petId))
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

    public ValidatableResponse updatePet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .put(UPDATE_PET)
                .then()
                .body("name", is(pet.getName()))
                .statusCode(SC_OK);

    }

    public ValidatableResponse updatePetByDataForm(long petId, String updatedPetName, Status updatedStatus) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .params("name", updatedPetName, "status", updatedStatus)
                .when()
                .post(UPDATE_PET_BY_DATAFORM, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);

    }

    public ValidatableResponse uploadPetImage(long petId, String fileName) {

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
        MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

        String petImageType = fileTypeMap.getContentType(file.getName()).split("/")[1];
        long petImageSize = FileUtils.sizeOf(file);

        return given()
                .contentType("multipart/form-data")
                .multiPart(file)
                .when()
                .post(UPLOAD_PET_IMAGE, petId)
                .then()
                .body("message", allOf(containsString(petImageType), containsString(String.valueOf(petImageSize)), containsString(file.getName())))
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
