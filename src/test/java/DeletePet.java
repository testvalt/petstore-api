import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class DeletePet {

    PetEndpoint petEndpoint = new PetEndpoint();

    long petId;

    @Before
    public void createTestPet() {
        int id = 0;
        String body = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"category\": {\n" +
                "    \"id\": \"" + id + "\",\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Scooby\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": \"" + id + "\",\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        ValidatableResponse response = petEndpoint.createPet(body);
        petId = response.extract().path("id");
    }

    @Test
    public void deleteMyPet() {
        petEndpoint.deletePet(petId);
    }
}