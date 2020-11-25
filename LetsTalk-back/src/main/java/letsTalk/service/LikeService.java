package letsTalk.service;

import java.util.List;
import java.util.Optional;

import letsTalk.model.Like_Class;

public interface LikeService {
	
	Optional<Like_Class> getOne(Long id);
	List<Like_Class> getAll();
	Like_Class save(Like_Class like);
	void delete(Long id);
}
