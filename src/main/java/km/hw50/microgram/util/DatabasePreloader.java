package km.hw50.microgram.util;

import km.hw50.microgram.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabasePreloader {

    @Bean
    CommandLineRunner generateGibberish(UserRepository usersRepo, PublicationRepository publicationRepo,
                                        CommentRepository commentRepo, SubscriptionRepository subscriptionRepo,
                                        LikeRepository likeRepo) {
        return args -> {
            usersRepo.deleteAll();
            publicationRepo.deleteAll();
            commentRepo.deleteAll();
            subscriptionRepo.deleteAll();
            likeRepo.deleteAll();
            var users = "";
//            usersRep.saveAll(users);
        };
    }
}
