package km.hw50.microgram.model;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Publication, String> {
}
// get publication date
// get publisher
// get publication liker's
