package km.hw50.microgram.util;

import km.hw50.microgram.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
public class DatabasePreloader {
    private Random r = new Random();

    @Bean
    CommandLineRunner generateGibberish(UserRepository usersRepo, PublicationRepository publicationRepo,
                                         SubscribtionRepository subscribtionRepo) {
        return args -> {
            // todo users repo initialization
            usersRepo.deleteAll();
            var users = User.makeUsers(10);
            usersRepo.saveAll(users);


            // todo publications repo initialization
            publicationRepo.deleteAll();
            int i = 0;
            String userId;
            List<Publication> publications = new ArrayList<>();
            while (i < 30) {
                userId = users.get(r.nextInt(users.size())).getId();
                var u = usersRepo.findById(userId).get();
                var p = Publication.make(u);
                publications.add(p);
                u.addPublication(p);
                usersRepo.save(u);
                i++;
            }
//            publicationRepo.saveAll(publications);
//            userId = users.get(4).getId();
//            System.out.println(userId);
//            var map = StreamSupport.stream(publicationRepo.findAll().spliterator(), true)
//                    .collect(Collectors.groupingBy(Publication::getUser, TreeMap::new, Collectors.toList()));
//            map.get(userId).forEach((v) -> System.out.println(v.getDescription() + "  " + v.getUser() + "\n"));

            //todo comments for publications
            i = 0;
            while (i < 20) {
                int pub = r.nextInt(publications.size());
                Publication commentFor = publications.get(pub);
                LocalDate pubDate = publications.get(pub).getDate();
                User commentator = users.get(r.nextInt(users.size()));
                var c = Comment.make(commentator, commentFor, pubDate);
                var u = usersRepo.findById(commentFor.getUser().getId()).get();
                u.getPublications().forEach(p -> {if (p.getId().equals(commentFor.getId())) p.addComment(c);});
                usersRepo.save(u);
                i++;
            }

            //todo likes for publications
            i = 0;
            while (i < 30) {
                int pub = r.nextInt(publications.size());
                User liker = users.get(r.nextInt(users.size()));
                Publication publ = publications.get(pub);
                LocalDate pubDate = publications.get(pub).getDate();
                var l = Like.make(liker, publ, pubDate);
                var u = usersRepo.findById(publ.getUser().getId()).get();
                u.getPublications().forEach(p -> { if (p.getId().equals(publ.getId())) p.addLike(l);});
                usersRepo.save(u);
                i++;
            }

            // todo subscribtions repo initialization
            subscribtionRepo.deleteAll();
            i = 0;
            List<Subscribtion> subscribtions = new ArrayList<>();
            while (i < 20) {
                int subT = r.nextInt(users.size() - 1);
                int subR = subT + 1 <= users.size() ? subT + 1 : subT - 1;
                var subscT = users.get(subT).getId();
                var subscR = users.get(subR).getId();
                var uSubT = usersRepo.findById(subscT).get();
                var uSubR = usersRepo.findById(subscR).get();
                uSubT.plusSubscribersCount();
                uSubR.plusSubscribtionsCount();
                usersRepo.save(uSubT);
                usersRepo.save(uSubR);
                subscribtions.add(Subscribtion.make(uSubT, uSubR));
                i++;
            }
            subscribtionRepo.saveAll(subscribtions);
            System.out.println("\nUsers:");
            usersRepo.findAll().forEach(u -> System.out.println(u));
            System.out.println("\nPublications:");
            usersRepo.findAll().forEach(u -> u.getPublications()
                    .forEach(p -> System.out.println(p)));
            System.out.println("\nComments:");
            usersRepo.findAll().forEach(u -> u.getPublications().
                    forEach(p -> p.getComments().forEach(c -> System.out.println(c))));
            System.out.println("\nSubscribtions");
            subscribtionRepo.findAll().forEach(s -> System.out.println(s));

        };
    }
}
