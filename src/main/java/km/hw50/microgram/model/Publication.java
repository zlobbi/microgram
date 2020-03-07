package km.hw50.microgram.model;

import km.hw50.microgram.util.Generator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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
    @DBRef
    private User user;
    private String image;
    private String description;
    private LocalDate date;
    private List<Comment> comments = new ArrayList<>();
    private List<Like> likes = new ArrayList<>();

    private static Random r = new Random();

    public static Publication make(User user) {
        Publication p = new Publication();
        String image = Generator.makeName();
        p.setId(valueOf(Objects.hash(image)));
        p.setImage(image.toLowerCase() + ".jpg");
        p.setUser(user);
        p.setDescription(Generator.makeDescription());
        p.setDate(LocalDate.now().minusDays(r.nextInt(20)+2));
        return p;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id='" + id + '\'' +
                ", userLogin='" + user.getLogin() + '\'' +
                ", image='" + image + '\'' +
                ", likes='" + (likes.size()) + '\'' +
                ", comments='" + (comments.size()) + '\'' +
                ", description=" + description + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public int getLikesCount() {
        return likes.size();
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

    public void removeComment(Comment coment) {
        this.comments.removeIf(c -> c.getId().equals(coment.getId()));
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void addLike(Like like) {
        this.likes.add(like);
    }

    public void removeLike(Like like) {
        this.likes.removeIf(l -> l.getId().equals(like.getId()));
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
