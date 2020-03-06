package km.hw50.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;

import static java.lang.String.valueOf;

@Document
public class Like {
    private static Random r = new Random();

    @Id
    private String id;
    private String likerId;
    private String likeFor;
    private LocalDate date;

//    public Like(String likerId, String likeFor, LocalDate date) {
//        this.likeFor = likeFor;
//        this.likerId = likerId;
//        this.date = date;
//    }
    public static Like make(String likerId, String likeFor, LocalDate likeForDate) {
        int t = LocalDate.now().compareTo(likeForDate);
        Like l = new Like();
        String id = valueOf((likerId + likeFor).hashCode());
        l.setId(id);
        l.setLikerId(likerId);
        l.setLikeFor(likeFor);
        l.setDate(LocalDate.now().minusDays(r.nextInt(t)+1));
        return l;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", likerId='" + likerId + '\'' +
                ", likeFor='" + likeFor + '\'' +
                ", date=" + date.toString() +
                '}';
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

    public String getLikerId() {
        return likerId;
    }

    public void setLikerId(String likerId) {
        this.likerId = likerId;
    }

//    public String getObjectId() {
//        return objectId;
//    }
//
//    public void setObjectId(String objectId) {
//        this.objectId = objectId;
//    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
