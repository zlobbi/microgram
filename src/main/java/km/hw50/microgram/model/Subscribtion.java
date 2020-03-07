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
    private User subscriber;
    private User subscribiant;
    private LocalDate date;

    private static Random r = new Random();

    public static Subscribtion make(User subscriber, User subscribiant) {
        Subscribtion s = new Subscribtion();
        s.setId(valueOf((subscriber.getId()+subscribiant.getId()).hashCode()));
        s.setSubscriber(subscriber);
        s.setSubscribiant(subscribiant);
        s.setDate(LocalDate.now().minusDays(r.nextInt(23)));
        return s;
    }

    @Override
    public String toString() {
        return "Subscriptions{" +
                "id='" + id + '\'' +
                ", subscriberLogin='" + subscriber.getLogin() + '\'' +
                ", subscribiantLogin='" + subscribiant.getLogin() + '\'' +
                ", date=" + date.toString() +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getSubscribiant() {
        return subscribiant;
    }

    public void setSubscribiant(User subscribiant) {
        this.subscribiant = subscribiant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
