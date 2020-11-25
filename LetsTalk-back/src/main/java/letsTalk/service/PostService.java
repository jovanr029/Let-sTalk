package letsTalk.service;

import java.util.List;
import java.util.Optional;

import letsTalk.model.Post;
import letsTalk.web.dto.PostDto;

public interface PostService {
	
	Optional<Post> getOne(Long id);
	List<PostDto> getAll();
	List<PostDto> getAllByUserId(Long id);
	List<PostDto> getAllPostsLoggedUser();
	Post save(Post post);
	Post save(PostDto postDto);
	Post edit(PostDto postDto);
	void delete(Long id);

}
