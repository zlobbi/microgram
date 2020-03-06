package km.hw50.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Like {

    @Id
    private String id;
    private String userId;
    private String likeFor;
    private String objectId;
    private LocalDate date;

    public Like(String userId, String likeFor, String objectId, LocalDate date) {
        this.likeFor = likeFor;
        this.userId = userId;
        this.objectId = objectId;
        this.date = date;
    }

    public String getLikeFor() {
        return likeFor;
    }

    public void setLikeFor(String likeFor) {
        this.likeFor = likeFor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
