package km.hw50.microgram.util;

import km.hw50.microgram.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.util.BsonUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Configuration
public class DatabasePreloader {
    private Random r = new Random();

    @Bean
    CommandLineRunner generateGibberish(UserRepository usersRepo, PublicationRepository publicationRepo,
                                        CommentRepository commentRepo, SubscribtionRepository subscribtionRepo,
                                        LikeRepository likeRepo) {
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
                var p = Publication.make(userId);
                publications.add(p);
                u.addPublication(p);
                usersRepo.save(u);
                i++;
            }
            publicationRepo.saveAll(publications);
            userId = users.get(4).getId();
            System.out.println(userId);
            var map = StreamSupport.stream(publicationRepo.findAll().spliterator(), true)
                    .collect(Collectors.groupingBy(Publication::getUserId, TreeMap::new, Collectors.toList()));
            map.get(userId).forEach((v) -> System.out.println(v.getDescription() + "  " + v.getUserId() + "\n"));

            //todo comments repo initialization
            commentRepo.deleteAll();
            i = 0;
            List<Comment> comments = new ArrayList<>();
            while (i < 20) {
                int pub = r.nextInt(publications.size());
                String pubId = publications.get(pub).getId();
                LocalDate pubDate = publications.get(pub).getDate();
                String usId = users.get(r.nextInt(users.size())).getId();
                var c = Comment.make(usId, pubId, pubDate);
                var publ = publicationRepo.findById(pubId).get();
                publ.addComment(c);
                publicationRepo.save(publ);
                comments.add(c);
                i++;
            }
            commentRepo.saveAll(comments);

            //todo likes repo initialization
            likeRepo.deleteAll();
            i = 0;
            List<Like> likes = new ArrayList<>();
            while (i < 30) {
                int pub = r.nextInt(publications.size());
                String likerId = users.get(r.nextInt(users.size())).getId();
                String pubId = publications.get(pub).getId();
                LocalDate pubDate = publications.get(pub).getDate();
                likes.add(Like.make(likerId, pubId, pubDate));
                i++;
            }
            likeRepo.saveAll(likes);

            // todo subscribtion repo initialization
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
//                if(subscT.equals(uSubT.getId()))
                uSubT.plusSubscribersCount();
                uSubR.plusSubscribtionsCount();
                usersRepo.save(uSubT);
                usersRepo.save(uSubR);
                subscribtions.add(Subscribtion.make(subscR, subscT));
                i++;
            }
            subscribtionRepo.saveAll(subscribtions);
            System.out.println("\nUsers:");
            usersRepo.findAll().forEach(u -> System.out.println(u));
            System.out.println("\nPublications:");
            publicationRepo.findAll().forEach(p -> System.out.println(p));
            System.out.println("\nComments:");
            commentRepo.findAll().forEach(c -> System.out.println(c));
            System.out.println("\nLikes:");
            likeRepo.findAll().forEach(l -> System.out.println(l));
            System.out.println("\nSubscribtions");
            subscribtionRepo.findAll().forEach(s -> System.out.println(s));

        };
    }
}
