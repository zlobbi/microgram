package km.hw50.microgram.model;

import km.hw50.microgram.util.Generator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static java.lang.String.valueOf;

@Document
public class Publication {
    @Id
    private String id;
    private String userId;
    private String image;
    private String description;
    private LocalDate date;
    private int likesCount;
    private List<Comment> comments = new ArrayList<>();

    private static Random r = new Random();

    public static Publication make(String userId) {
        Publication p = new Publication();
        String image = Generator.makeName();
        p.setId(valueOf(Objects.hash(image)));
        p.setImage(image.toLowerCase() + ".jpg");
        p.setUserId(userId);
        p.setDescription(Generator.makeDescription());
        p.setDate(LocalDate.now().minusDays(r.nextInt(20)+2));
        return p;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", image='" + image + '\'' +
                ", description=" + description + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void plusLikesCount() {
        this.likesCount = likesCount;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
