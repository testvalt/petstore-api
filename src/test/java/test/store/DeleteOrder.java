package test.store;

import endPoint.PetEndpoint;
import endPoint.StoreEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.OrderStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SerenityRunner.class)
public class DeleteOrder {

    @Steps
    private StoreEndpoint storeEndpoint;
    private long orderId;

    @Before
    public void createOrder() {
        Order order = Order.builder()
                .id(ThreadLocalRandom.current().nextInt(1, 1000 + 1))
                .petId(1)
                .quantity(1)
                .shipDate(String.valueOf(System.currentTimeMillis()))
                .status(OrderStatus.PLACED)
                .complete(true)
                .build();
        ValidatableResponse response = storeEndpoint.createOrder(order);
        orderId = response.extract().path("id");
    }

    @Test
    public void deleteOrder() {
        storeEndpoint.deleteOrder(orderId);
    }
}
