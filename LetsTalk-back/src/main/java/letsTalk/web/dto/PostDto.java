package letsTalk.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDto {

	private Long id;
	@NotBlank
	@Size(max = 25)
	private String title;
	private String content;
	
	private String username;
	private String user;
	
}
