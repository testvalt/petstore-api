package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private long id;
    private long petId;
    private int quantity;
    private String shipDate;
    private OrderStatus status;
    private boolean complete;
}
