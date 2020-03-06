package km.hw50.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Comment {

    @Id
    private String id;
    private String userId;
    private String commentFor;
    private String tekst;
    private LocalDate date;

    public Comment(String userId, String commentFor, String tekst, LocalDate date) {
        this.userId = userId;
        this.commentFor = commentFor;
        this.tekst = tekst;
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentFor() {
        return commentFor;
    }

    public void setCommentFor(String commentFor) {
        this.commentFor = commentFor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
