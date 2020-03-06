package km.hw50.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Subscribtion {

    @Id
    private String id;
    private String subscriber;
    private String subscribiant;
    private LocalDate date;

    public Subscribtion(String subscriber, String subscribiant, LocalDate date) {
        this.subscriber = subscriber;
        this.subscribiant = subscribiant;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getSubscribiant() {
        return subscribiant;
    }

    public void setSubscribiant(String subscribiant) {
        this.subscribiant = subscribiant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
