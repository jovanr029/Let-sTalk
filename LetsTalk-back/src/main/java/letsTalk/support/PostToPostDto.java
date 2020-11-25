package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.Post;
import letsTalk.web.dto.PostDto;

@Component
public class PostToPostDto implements Converter<Post, PostDto>{

	@Override
	public PostDto convert(Post source) {
		PostDto target = new PostDto();
		target.setId(source.getId());
		target.setUser(source.getUser().getFirstName() + " "  + source.getUser().getLastName());
		target.setUsername(source.getUser().getUsername());
		target.setTitle(source.getTitle());
		target.setContent(source.getContent());
		
		return target;
	}
	
	public List<PostDto> convert(List<Post> posts){
		List<PostDto> target = new ArrayList<>();
		
		for(Post p : posts) {
			PostDto dto = convert(p);
			target.add(dto);
		}
		return target;
	}

}
