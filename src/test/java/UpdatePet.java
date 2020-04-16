import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePet {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long petId;

    @Before
    public void createPet() {
        Pet pet = new Pet(0, "Scooby", "available");
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }

    @After
    public void deletePet() {

        petEndpoint.deletePet(petId);
    }

    @Test
    public void updatePetsName() {
        Pet pet = new Pet(petId, "Snoopy", "pending");
        petEndpoint.updatePet(pet, petId);
    }
}