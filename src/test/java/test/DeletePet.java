package test;

import endPoint.PetEndpoint;
import model.Pet;
import model.Status;
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
    private int petId;

    @Before
    public void createPet() {
        Pet pet = Pet.builder()
                .id(0)
                .name("Scooby")
                .status(Status.AVAILABLE)
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