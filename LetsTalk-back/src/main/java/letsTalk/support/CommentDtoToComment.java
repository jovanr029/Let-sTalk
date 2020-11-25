package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import letsTalk.model.Comment;
import letsTalk.model.Post;
import letsTalk.model.User;
import letsTalk.security.IAuthenticationFacade;
import letsTalk.service.CommentService;
import letsTalk.service.PostService;
import letsTalk.service.UserService;
import letsTalk.web.dto.CommentDto;

@Component
public class CommentDtoToComment implements Converter<CommentDto, Comment> {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@Override
	public Comment convert(CommentDto source) {
		String username = authenticationFacade.currentUserNameSimple();		
		User user = userService.byUsername(username).get();
		
		Post post = null;
		if(source.getPostId() != null) {
			post = postService.getOne(source.getPostId()).get();
		}
		
		if(user != null && post != null) {
			Long id = source.getId();
			Comment comment = id == null? new Comment() : commentService.getOne(id).get();
			
			if(comment != null) {
				comment.setId(source.getId());
				comment.setUser(user);
				comment.setPost(post);
				comment.setText(source.getText());
			}
			
			return comment;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}		
	}
	
	public List<Comment> convert(List<CommentDto> source){
		List<Comment> target = new ArrayList<>();
		for(CommentDto dto : source) {
			Comment c = convert(dto);
			target.add(c);
		}
		
		return target;	
	}

}
