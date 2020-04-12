import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePet {

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

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void updatePetsName() {
        String body = "{\n" +
                "  \"id\": \"" + petId + "\",\n" +
                "  \"category\": {\n" +
                "    \"id\": \"" + petId + "\",\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"Snoopy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": \"" + petId + "\",\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
        petEndpoint.updatePet(body);
    }
}