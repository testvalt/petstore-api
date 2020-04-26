import java.util.Arrays;
import java.util.List;

public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Object> tags;
    private Status status;

    public Pet(long id, String name, Status status) {
        this.id = id;
        this.category = new Category("0", "Scooby");
        this.name = name;
        this.photoUrls = Arrays.asList("https://upload.wikimedia.org/wikipedia/en/9/9a/Scooby-gang-1969.jpg");
        this.tags = Arrays.asList(new Tags("0", "Scooby"));
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public Object getCategory() {
        return category;
    }

    public String getName() { return name; }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Object> getTags() {
        return tags;
    }

    public Status getStatus() {
        return status;
    }

}
