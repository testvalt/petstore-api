package test.store;

import endPoint.PetEndpoint;
import endPoint.StoreEndpoint;
import model.Pet;
import model.PetStatus;
import model.Category;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetPetInventoriesByStatus {

    @Steps
    private PetEndpoint petEndpoint;
    private long petId;

    @Steps
    private StoreEndpoint storeEndpoint;

    @Before
    public void createPet() {
        Pet pet = Pet.builder()
                .id(0)
                .name("Scooby")
                .status(PetStatus.AVAILABLE)
                .category(Category.builder().build())
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void getPetInventoriesByStatus() {
        storeEndpoint.getPetInventoriesByStatus();
    }

}
