package km.hw50.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Random;

import static java.lang.String.valueOf;

@Document
public class Subscribtion {

    @Id
    private String id;
    private String subscriberId;
    private String subscribiantId;
    private LocalDate date;

    private static Random r = new Random();

    public static Subscribtion make(String subscriberId, String subscribiantId) {
        Subscribtion s = new Subscribtion();
        s.setId(valueOf((subscriberId+subscribiantId).hashCode()));
        s.setSubscriber(subscriberId);
        s.setSubscribiant(subscribiantId);
        s.setDate(LocalDate.now().minusDays(r.nextInt(23)));
        return s;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id='" + id + '\'' +
                ", subscriberId='" + subscriberId + '\'' +
                ", subscribiantId='" + subscribiantId + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscriber() {
        return subscriberId;
    }

    public void setSubscriber(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getSubscribiant() {
        return subscribiantId;
    }

    public void setSubscribiant(String subscribiant) {
        this.subscribiantId = subscribiant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
