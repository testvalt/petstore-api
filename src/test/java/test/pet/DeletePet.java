package test.pet;

import endPoint.PetEndpoint;
import model.Pet;
import model.PetStatus;
import model.Category;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeletePet {

    @Steps
    private PetEndpoint petEndpoint;
    private long petId;

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

    @Test
    public void deleteMyPet() {
        petEndpoint.deletePet(petId);
    }

}