package letsTalk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import letsTalk.model.LikeIdEmbeddable;
import letsTalk.model.Like_Class;

@Repository
public interface LikeRepository extends JpaRepository<Like_Class, LikeIdEmbeddable> {
	
	List<Like_Class> findByPostId(Long postId);
}
