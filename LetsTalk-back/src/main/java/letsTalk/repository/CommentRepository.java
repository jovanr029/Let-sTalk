package letsTalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import letsTalk.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByPostId(Long idPost);

}
