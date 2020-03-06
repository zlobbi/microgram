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
                                        CommentRepository commentRepo, SubscribtionRepository subscribtionRepo,
                                        LikeRepository likeRepo) {
        return args -> {
            // todo users repo initialization
            System.out.println("\nUsers:");
            usersRepo.deleteAll();
            var users = User.makeUsers(10);
            usersRepo.saveAll(users);
            usersRepo.findAll().forEach(u -> System.out.println(u));


            // todo publications repo initialization
            System.out.println("\nPublications:");
            publicationRepo.deleteAll();
            int i = 0;
            String userId;
            List<Publication> publications = new ArrayList<>();
            while (i < 30) {
                userId = users.get(r.nextInt(users.size())).getId();
                publications.add(Publication.make(userId));
                i++;
            }
            publicationRepo.saveAll(publications);
            publicationRepo.findAll().forEach(p -> System.out.println(p));
//            userId = users.get(4).getId();
//            System.out.println(userId);
//            var map = StreamSupport.stream(publicationRepo.findAll().spliterator(), true)
//                    .collect(Collectors.groupingBy(Publication::getUserId, TreeMap::new, Collectors.toList()));
//            map.get(userId).forEach((v) -> System.out.println(v.getDescription() + "  " + v.getUserId() + "\n"));

            //todo comments repo initialization
            System.out.println("\nComments:");
            commentRepo.deleteAll();
            i = 0;
            List<Comment> comments = new ArrayList<>();
            while(i < 20) {
                int pub = r.nextInt(publications.size());
                String pubId = publications.get(pub).getId();
                LocalDate pubDate = publications.get(pub).getDate();
                String usId = users.get(r.nextInt(10)).getId();
                comments.add(Comment.make(usId, pubId, pubDate));
                i++;
            }
            commentRepo.saveAll(comments);
            commentRepo.findAll().forEach(c -> System.out.println(c));

            //todo likes repo initialization
            System.out.println("\nLikes:");
            likeRepo.deleteAll();
            i = 0;
            List<Like> likes = new ArrayList<>();
            while(i < 30) {
                int pub = r.nextInt(publications.size());
                String likerId = users.get(r.nextInt(users.size())).getId();
                String pubId = publications.get(pub).getId();
                LocalDate pubDate = publications.get(pub).getDate();
                likes.add(Like.make(likerId, pubId, pubDate));
                i++;
            }
            likeRepo.saveAll(likes);
            likeRepo.findAll().forEach(l -> System.out.println(l));

            subscribtionRepo.deleteAll();

        };
    }
}
