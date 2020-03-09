package km.hw50.microgram.controler;

import km.hw50.microgram.model.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Controller
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

    @GetMapping
    public String rootHandler(Model model) {
        var map = StreamSupport.stream(userRepo.findAll().spliterator(), true)
                .collect(Collectors.toList());
        map.forEach((v) -> System.out.println(v));
        model.addAttribute("users", map);
        return "index";
    }
}
