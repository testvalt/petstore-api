package test.store;

import endPoint.PetEndpoint;
import endPoint.StoreEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.OrderStatus;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadLocalRandom;

@RunWith(SerenityRunner.class)
public class CreateNewOrder {

    @Steps
    private StoreEndpoint storeEndpoint;
    private long orderId;

    @After
    public void deleteOrder() {
        storeEndpoint.deleteOrder(orderId);
    }

    @Test
    public void createOrder() {
        Order order = Order.builder()
                .id(0)
                //.id(ThreadLocalRandom.current().nextInt(1, 1000 + 1))
                .petId(0)
                .quantity(1)
                .shipDate(String.valueOf(System.currentTimeMillis()))
                .status(OrderStatus.PLACED)
                .complete(true)
                .build();
        ValidatableResponse response = storeEndpoint.createOrder(order);
       // System.out.println(orderId);
      //  System.out.println("stanislav");
        orderId = response.extract().path("id");

        System.out.println(orderId);


    }

}
