package test;

import endPoint.PetEndpoint;
import model.Pet;
import model.Status;
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
public class UploadPetImage {

    @Steps
    private PetEndpoint petEndpoint;
    private int petId;
    private final String fileName;

    public UploadPetImage(String fileName) {
        this.fileName = fileName;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"123456789.jpeg"},
                {"a.jpeg"},
                {"gif_image.gif"},
                {"Large_poster_500kb_image.jpeg"},
                {"ScOOby7777$$$555_@miXXED66,,name_image.jpeg"},
                {"scooby_jpeg_image.jpeg"},
                {"scooby_png_image.png"},
                {"SCOOBYUPPERCASENAME_IMAGE.jpeg"},
                {"scoobyverylongnmameeeeeeeeeeeeeeeeeeee_image.jpeg"}
        });
    }

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

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void uploadJpegPetImage() {
        petEndpoint.uploadPetImage(petId, fileName);
    }

}
