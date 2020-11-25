package letsTalk.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import letsTalk.exceptions.BadRequestException;
import letsTalk.exceptions.ResourceNotFoundException;
import letsTalk.model.Post;
import letsTalk.repository.PostRepository;
import letsTalk.security.IAuthenticationFacade;
import letsTalk.service.PostService;
import letsTalk.service.UserService;
import letsTalk.support.PostDtoToPost;
import letsTalk.support.PostToPostDto;
import letsTalk.web.dto.PostDto;

@Service
public class JpaPostService implements PostService {

	@Autowired
	private PostRepository postRepository; 
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostToPostDto toDto;
	
	@Autowired
	private PostDtoToPost toPost;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Override
	public Optional<Post> getOne(Long id) {	
		return postRepository.findById(id);
	}
	
	@Override
	public List<PostDto> getAllByUserId(Long userId) {
		
		if(!userService.getOne(userId).isPresent()) {
			throw new ResourceNotFoundException("No user with id: " + userId);
		}
		
		return toDto.convert(postRepository.findByUserId(userId));
	}

	@Override
	public List<PostDto> getAllPostsLoggedUser() {
		String username = authenticationFacade.currentUserNameSimple();	
		if(username == null) {
			throw new ResourceNotFoundException("You are not logged in");
		}
		return toDto.convert(postRepository.findByUserUsername(username));
	}


	@Override
	public List<PostDto> getAll() {	
		return toDto.convert(postRepository.findAll());
	}

	@Override
	public Post save(Post post) {
		return postRepository.save(post);
	}

	@Override
	public void delete(Long id) {
		postRepository.deleteById(id);
	}

	@Override
	public Post save(PostDto postDto) {
		if(postDto.getId() != null) {
			throw new BadRequestException("Post id must be null") ;
		}		
		Post postToSave = toPost.convert(postDto); 
		return postRepository.save(postToSave) ;
	}

	@Override
	public Post edit(PostDto postDto) {
		Post postToSave = toPost.convert(postDto); 
		return postRepository.save(postToSave) ;
	}

}
