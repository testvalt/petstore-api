package test.store;

import endPoint.PetEndpoint;
import endPoint.StoreEndpoint;
import io.restassured.response.ValidatableResponse;
import model.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SerenityRunner.class)
public class GetOrderById {

    @Steps
    private PetEndpoint petEndpoint;
    private StoreEndpoint storeEndpoint;
    private long petId;
    private long orderId;

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
    
    public void createOrder() {
        Order order = Order.builder()
                .id(ThreadLocalRandom.current().nextInt(1, 1000 + 1))
                .petId(petId)
                .quantity(1)
                .shipDate(String.valueOf(System.currentTimeMillis()))
                .status(OrderStatus.PLACED)
                .complete(true)
                .build();
        ValidatableResponse response = storeEndpoint.createOrder(order);
        orderId = response.extract().path("id");
    }

    @After
    public void deleteOrder() {
        storeEndpoint.deleteOrder(orderId);
    }

    public void deletePet() {
        petEndpoint.deletePet(petId);
    }

    @Test
    public void getOrder() {
        storeEndpoint.getOrder(orderId);
    }

}
