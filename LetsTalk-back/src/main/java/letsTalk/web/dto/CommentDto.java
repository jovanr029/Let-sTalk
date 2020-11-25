package letsTalk.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentDto {

	private Long id;		
	private String text;

	private String user;
	
	private Long postId;
	private String post;
}
