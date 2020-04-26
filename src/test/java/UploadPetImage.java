import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UploadPetImage {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long petId;

    @Before
    public void createPet() {
        Pet pet = new Pet(0, "Scooby", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void uploadJpegPetImage() {
        petEndpoint.uploadPetImage(petId, "scooby_image_supported.jpeg");
    }

    @Test
    public void uploadPngPetImage() {
        petEndpoint.uploadPetImage(petId, "scooby_image_supported.png");
    }

}
