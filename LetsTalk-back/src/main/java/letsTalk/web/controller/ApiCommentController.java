package letsTalk.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import letsTalk.service.CommentService;
import letsTalk.support.CommentToCommentDto;
import letsTalk.web.dto.CommentDto;

@RestController
@RequestMapping("/api/comments")
public class ApiCommentController {
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentToCommentDto toDto;
	
	@GetMapping("/post/{idPost}")
	@ResponseBody
	public List<CommentDto> getCommentsForPost(@PathVariable Long idPost){
		return commentService.getAllForPost(idPost);
	}
	
	@PostMapping("/post")
	public CommentDto add(@RequestBody CommentDto commentDto) {
		return toDto.convert(commentService.save(commentDto));  
	}

}
