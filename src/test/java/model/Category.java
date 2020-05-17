package model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Category {
    private String id;
    private String name;
}
