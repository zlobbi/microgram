package km.hw50.microgram.model;

import km.hw50.microgram.util.Generator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.valueOf;

@Document
public class User {

    @Id
    private String id;
    private String login;
    private String email;
    private String password;
    private List<Publication> publications = new ArrayList<>();
    private int subscribersCount;
    private int subscribtionsCount;
    private List<User> subscribtions = new ArrayList<>();
    private List<User> subscribers = new ArrayList<>();

    public static User make() {
        User u = new User();
        String name = Generator.makeName().toLowerCase();
        u.setId(valueOf(Objects.hash(name)));
        u.setLogin(name);
        u.setEmail(Generator.makeEmail() + ".com");
        u.setPassword(Generator.makePassword());
        return u;
    }

    public static User make(String login) {
        User u = new User();
        u.setLogin(login);
        return u;
    }

    public static List<User> makeUsers(int count) {
        List<User> users = new ArrayList<>();
        while (users.size() != count) {
            users.add(make());
        }
        return users;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password + '\'' +
                ", subscribers=" + (subscribersCount) + '\'' +
                ", subscribtions=" + (subscribtionsCount) + '\'' +
                ", publications=" + (publications.size()) +
                '}';
    }

    // Publications actions -----------------------------
    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public void addPublication(Publication p) {
        this.publications.add(p);
    }

    public void removePublication(Publication p) {
        this.publications.removeIf(pu -> pu.getId().equals(p.getId()));
    }

    public int getPublicationsCount() {
        return publications.size();
    }

    // Subscribtions actions -----------------------------

    public void plusSubscribtionsCount() {
        this.subscribtionsCount++;
    }

    public void minusSubscribtionsCount() {
        this.subscribtionsCount--;
    }

    public int getSubscribtionsCount() {
        return this.subscribtionsCount;
    }


    // Subscribers actions -----------------------------

    public void plusSubscribersCount() {
        this.subscribersCount++;
    }

    public void minusSubscribersCount() {
        this.subscribersCount--;
    }

    public int getSubscribersCount() {
       return this.subscribersCount;
    }

    // Default actions
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
