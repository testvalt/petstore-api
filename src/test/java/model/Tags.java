package model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Tags {
    private String id;
    private String name;
}

//public class Tags {
//    private String id;
//    private String name;
//
//    public Tags(String id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//}
