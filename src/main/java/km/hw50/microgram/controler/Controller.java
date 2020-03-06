package km.hw50.microgram.controler;

import km.hw50.microgram.model.*;

public class Controller {
    private final UserRepository userRepo;
    private final PublicationRepository publicationRepo;
    private final CommentRepository commentRepo;
    private final LikeRepository likeRepo;
    private final SubscriptionRepository subscriptionRepo;

    public Controller(UserRepository userRepo, PublicationRepository publicationRepo,
                      CommentRepository commentRepo, LikeRepository likeRepo,
                      SubscriptionRepository subscriptionRepo) {
        this.userRepo = userRepo;
        this.publicationRepo = publicationRepo;
        this.commentRepo = commentRepo;
        this.likeRepo = likeRepo;
        this.subscriptionRepo = subscriptionRepo;
    }
}
