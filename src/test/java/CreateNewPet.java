import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;

public class CreateNewPet {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long petId;

    @After
    public void deletePet() {

        petEndpoint.deletePet(petId);
    }

    @Test
    public void createMyPet() {
        Pet pet = new Pet(0, "Scooby", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }
}
