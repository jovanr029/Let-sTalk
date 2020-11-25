package letsTalk.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import letsTalk.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findFirstByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE "
			+ "(:username IS NULL or u.username like :username ) AND "
			+ "(:firstName IS NULL OR u.firstName like :firstName) AND "
			+ "(:lastName IS NULL or u.lastName like :lastName ) "
			)
	Page<User> search(
			@Param("username") String username,
			@Param("firstName") String firstName,
			@Param("lastName") String lastName,
			Pageable pageRequest);

}
