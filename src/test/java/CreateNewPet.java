import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateNewPet {

    PetEndpoint petEndpoint = new PetEndpoint();

    long petId;

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void createMyPet() {
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
}
