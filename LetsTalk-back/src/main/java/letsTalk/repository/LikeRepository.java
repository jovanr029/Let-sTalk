package letsTalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import letsTalk.model.Like_Class;

@Repository
public interface LikeRepository extends JpaRepository<Like_Class, Long> {
	
}
