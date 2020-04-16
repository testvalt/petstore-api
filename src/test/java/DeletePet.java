import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class DeletePet {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long petId;

    @Before
    public void createPet() {
        Pet pet = new Pet(0, "Scooby", "available");
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }

    @Test
    public void deleteMyPet() {

        petEndpoint.deletePet(petId);
    }
}