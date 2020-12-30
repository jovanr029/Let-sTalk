package letsTalk.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import letsTalk.exceptions.BadRequestException;
import letsTalk.model.Like_Class;
import letsTalk.repository.LikeRepository;
import letsTalk.service.LikeService;

@Service
public class JpaLikeService implements LikeService {
	
	@Autowired
	private LikeRepository likeRepository;

	@Override
	public List<Like_Class> getAll() {
		return likeRepository.findAll();
	}
	
	@Override
	public List<Like_Class> getAllForPost(Long postId) {
		return likeRepository.findByPostId(postId);
	}

	@Override
	public Like_Class save(Like_Class like)  {
		if(!like.getId().getPost().equals(like.getPost().getId())
			|| !like.getId().getUser().equals(like.getUser().getId())) {
				throw new BadRequestException("Bad request");
		}
		
		return likeRepository.save(like);
	}

	@Override
	public Optional<Like_Class> getOne(Long id) {
		return null;
	}

	@Override
	public void delete(Long id) {
		
	}	

}
