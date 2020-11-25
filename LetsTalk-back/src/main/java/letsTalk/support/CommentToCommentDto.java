package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.Comment;
import letsTalk.web.dto.CommentDto;

@Component
public class CommentToCommentDto implements Converter<Comment, CommentDto> {

	@Override
	public CommentDto convert(Comment source) {	
		CommentDto target = new CommentDto();
		target.setId(source.getId());
		target.setUser(source.getUser().getFirstName() + " " + source.getUser().getLastName());
		target.setPostId(source.getPost().getId());
		target.setPost(source.getPost().getContent());
		target.setText(source.getText());
		return target;
	}
	
	public List<CommentDto> convert(List<Comment> source){
		List<CommentDto> target = new ArrayList<>();
		for(Comment c : source) {
			CommentDto dto = convert(c);
			target.add(dto);
		}
		return target;	
	}

}
