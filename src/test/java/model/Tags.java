package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tags {
    private String id;
    private String name;
}
