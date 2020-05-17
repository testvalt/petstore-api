package test;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.PetOrder;
import model.PetOrderStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SerenityRunner.class)
public class CreateNewPetOrder {

    @Steps
    private PetEndpoint petEndpoint;
    private int petOrderId;

    @After
    public void deleteOrder() {
        petEndpoint.deletePetOrder(petOrderId);
    }

    @Test
    public void createOrder() {
        PetOrder order = PetOrder.builder()
                .id(ThreadLocalRandom.current().nextInt(1, 1000 + 1))
                .petId(1)
                .quantity(1)
                .shipDate(String.valueOf(System.currentTimeMillis()))
                .status(PetOrderStatus.PLACED)
                .complete(true)
                .build();
        ValidatableResponse response = petEndpoint.createPetOrder(order);
        petOrderId = response.extract().path("id");

    }

}
