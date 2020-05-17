package test;

import endPoint.PetEndpoint;
import model.Pet;
import model.Status;
import model.Category;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class CreateNewPet {

    @Steps
    private PetEndpoint petEndpoint;
    private long petId;

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void createMyPet() {
        Pet pet = Pet.builder()
                .id(0)
                .name("Scooby")
                .status(Status.AVAILABLE)
                .category(Category.builder().build())
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }

}
