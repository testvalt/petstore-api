package test.pet;

import endPoint.PetEndpoint;
import model.Pet;
import model.PetStatus;
import model.Category;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.TestData;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class GetPetByStatus {

    @Steps
    private PetEndpoint petEndpoint;
    private long petId;
    private final PetStatus status;

    public GetPetByStatus(PetStatus status) {
        this.status = status;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {PetStatus.AVAILABLE},
                {PetStatus.PENDING},
                {PetStatus.SOLD}
        });
    }

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
    public void getPetByStatus() {
        petEndpoint.getPetByStatus(status);
    }

}