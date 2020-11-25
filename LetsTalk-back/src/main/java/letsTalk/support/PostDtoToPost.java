package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.Post;
import letsTalk.model.User;
import letsTalk.security.IAuthenticationFacade;
import letsTalk.service.PostService;
import letsTalk.service.UserService;
import letsTalk.web.dto.PostDto;

@Component
public class PostDtoToPost implements Converter<PostDto, Post> {

	@Autowired
	private PostService postService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	public Post convert(PostDto source) {
		
		// username of currnetly logged in user from jwt
		String username = authenticationFacade.currentUserNameSimple();
		//user that is currently logged in 
		User user = userService.byUsername(username).get();
		if(user!=null) {
			Long id = source.getId();
			Post post = id == null? new Post() : postService.getOne(id).get();
			
			if(post!=null) {
				post.setId(source.getId());
				post.setTitle(source.getTitle());
				post.setContent(source.getContent());
				post.setUser(user);
			}
	
			return post;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}		
	}
	
	public List<Post> convert(List<PostDto> source){
		List <Post> target = new ArrayList<>();
		for(PostDto pdto : source) {
			Post p = convert(pdto);
			target.add(p);
		}
		return target;
	}

}
