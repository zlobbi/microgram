package km.hw50.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;

import static java.lang.String.valueOf;

@Document
public class Like {
    private static Random r = new Random();

    @Id
    private String id;
    @DBRef
    private Publication likeForPublication;
    @DBRef
    private User likeByUser;
    private LocalDate date;


    public static Like make(User likeByUser, Publication likeForPublication, LocalDate likeForDate) {
        int t = LocalDate.now().compareTo(likeForDate);
        Like l = new Like();
        String id = valueOf((likeByUser + likeForPublication.getId()).hashCode());
        l.setId(id);
        l.setLikerUser(likeByUser);
        l.setLikeForPublication(likeForPublication);
        l.setDate(LocalDate.now().minusDays(r.nextInt(t)+1));
        return l;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", liker login='" + likeByUser.getLogin() + '\'' +
                ", likeFor='" + likeForPublication.getImage() + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public Publication getLikedComment() {
        return likeForPublication;
    }

    public void setLikeForPublication(Publication likeForPublication) {
        this.likeForPublication = likeForPublication;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getLikdedUser() {
        return likeByUser;
    }

    public void setLikerUser(User likeByUser) {
        this.likeByUser = likeByUser;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
