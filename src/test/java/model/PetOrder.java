package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PetOrder {
    private int id;
    private int petId;
    private int quantity;
    private String shipDate;
    private PetOrderStatus status;
    private boolean complete;
}
