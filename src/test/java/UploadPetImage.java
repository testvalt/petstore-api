import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UploadPetImage {

    private PetEndpoint petEndpoint = new PetEndpoint();
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
    public void uploadNonImageFile() {
        petEndpoint.uploadPetImage(petId, "scooby_non_image_file.pdf");
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

}
