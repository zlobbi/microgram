package km.hw50.microgram.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

//    static void setPublicationCountForUser(String userId) {
//        this.findById(userId).get().plusPublicationCount();
//    }
}
// add searching user by name
// verify user existence by email
// get user publications list
// get comments for publication
// get user subscriptions count
// get user subscribers count
