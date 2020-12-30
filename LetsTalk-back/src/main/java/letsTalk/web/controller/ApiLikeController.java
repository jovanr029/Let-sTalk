package letsTalk.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import letsTalk.service.LikeService;
import letsTalk.support.LikeToLikeDto;
import letsTalk.web.dto.LikeDto;

@RestController
@RequestMapping("/api/likes")
public class ApiLikeController {
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private LikeToLikeDto toDto;
	
	@GetMapping("/post/{postId}")
	@ResponseBody
	public List<LikeDto> getAllLikesForPost(@PathVariable Long postId){
		return toDto.convert(likeService.getAllForPost(postId));
	}

}
