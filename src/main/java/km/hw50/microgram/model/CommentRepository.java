package km.hw50.microgram.model;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository <Comment, String> {
}
// get commentator
// get comment for publication
// get comment text
// get comment date
// delete comment
// update or change comment
