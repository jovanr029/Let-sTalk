package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.LikeIdEmbeddable;
import letsTalk.model.Like_Class;
import letsTalk.model.Post;
import letsTalk.model.User;
import letsTalk.security.IAuthenticationFacade;
import letsTalk.service.PostService;
import letsTalk.service.UserService;
import letsTalk.web.dto.LikeDto;

@Component
public class LikeDtoToLike implements Converter<LikeDto, Like_Class> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Override
	public Like_Class convert(LikeDto source) {
		String username = authenticationFacade.currentUserNameSimple();
		User user = userService.byUsername(username).get();
		
		Post post = null;
		if(source.getPostId() != null) {
			post = postService.getOne(source.getPostId()).get();
		}
		
		if(user != null && post != null) {
			Like_Class target = new Like_Class();
			target.setId(new LikeIdEmbeddable(user, post));
			target.setPost(post);
			target.setUser(user);
		
			return target;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}
	
	public List<Like_Class> convert(List<LikeDto> source) {
		List<Like_Class> target = new ArrayList<>();
		for(LikeDto l : source) {
			target.add(convert(l));
		}
		return target;
	}
	
		
}
