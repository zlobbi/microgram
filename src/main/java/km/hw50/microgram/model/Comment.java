package km.hw50.microgram.model;

import km.hw50.microgram.util.Generator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;

import static java.lang.String.valueOf;

@Document
public class Comment {
    private static Random r = new Random();

    @Id
    private String id;
    private User commentator;
    private Publication commentFor;
    private String text;
    private LocalDate date;

//    public Comment(String userId, String commentFor, String text, LocalDate date) {
//        this.userId = userId;
//        this.commentFor = commentFor;
//        this.text = text;
//        this.date = date;
//    }

    public static Comment make(User comentator, Publication commentFor, LocalDate publicationDate) {
        int t = LocalDate.now().compareTo(publicationDate);
        String text = Generator.makeDescription();
        Comment c = new Comment();
        String id = valueOf(text.indent(1).hashCode());
        c.setId(id);
        c.setCommentator(comentator);
        c.setCommentFor(commentFor);
        c.setText(text);
        c.setDate(LocalDate.now().minusDays(r.nextInt(t)+1));
        return c;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentatorLogin='" + commentator.getLogin() + '\'' +
                ", commentFor='" + commentFor.getImage() + '\'' +
                ", text=" + text + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public Publication getCommentFor() {
        return commentFor;
    }

    public void setCommentFor(Publication commentFor) {
        this.commentFor = commentFor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
