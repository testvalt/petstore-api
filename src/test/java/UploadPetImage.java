import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UploadPetImage {

    @Steps
    private PetEndpoint petEndpoint;
    private long petId;

    @Before
    public void createPet() {
        Pet pet = new Pet(0, "Scooby", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        petId = response.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void uploadJpegPetImage() {
        petEndpoint.uploadPetImage(petId, "scooby_jpeg_image.jpeg");
    }

    @Test
    public void uploadPngPetImage() {
        petEndpoint.uploadPetImage(petId, "scooby_png_image.png");
    }

    @Test
    public void uploadImageWithShortName() {
        petEndpoint.uploadPetImage(petId, "a.jpeg");
    }

    @Test
    public void uploadImageWithLongName() {
        petEndpoint.uploadPetImage(petId, "scoobyverylongnmameeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee.jpeg");
    }

    @Test
    public void uploadImageWithUppercaseName() {
        petEndpoint.uploadPetImage(petId, "SCOOBYUPPERCASENAME.jpeg");
    }

    @Test
    public void uploadImageWithNumbersName() {
        petEndpoint.uploadPetImage(petId, "123456789.jpeg");
    }

    @Test
    public void uploadImageWithMixedSymbolsName() {
        petEndpoint.uploadPetImage(petId, "ScOOby7777$$$555_@miXXED66,,name.jpeg");
    }

    @Test
    public void uploadLargeImage() {
        petEndpoint.uploadPetImage(petId, "Large_poster_500kb_image.jpeg");
    }

    @Test
    public void uploadGifImage() {
        petEndpoint.uploadPetImage(petId, "gif_image.gif");
    }

}
