package model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Object> tags;
    private PetStatus status;
}
