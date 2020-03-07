package km.hw50.microgram.controler;

import km.hw50.microgram.model.*;

public class Controller {
    private final UserRepository userRepo;
    private final PublicationRepository publicationRepo;
    private final SubscribtionRepository subscribtionRepo;

    public Controller(UserRepository userRepo, PublicationRepository publicationRepo,
                      SubscribtionRepository subscriptionRepo) {
        this.userRepo = userRepo;
        this.publicationRepo = publicationRepo;
        this.subscribtionRepo = subscriptionRepo;
    }
}
