import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdatePetByDataForm {

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
    public void updatePetByForm() {

        petEndpoint.updatePetByDataForm(petId);
    }
}