package letsTalk.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import letsTalk.exceptions.BadRequestException;
import letsTalk.exceptions.ResourceNotFoundException;
import letsTalk.model.Post;
import letsTalk.service.PostService;
import letsTalk.support.PostToPostDto;
import letsTalk.web.dto.PostDto;

@RestController
@RequestMapping("/api/posts")
public class ApiPostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostToPostDto toDto;
	
	@GetMapping("/{id}")
	@ResponseBody
	public PostDto getOne(@PathVariable Long id) {
		Optional<Post> post = postService.getOne(id);
		if(!post.isPresent()) {
			throw new ResourceNotFoundException("No post with id: " + id);
		}
		return toDto.convert(post.get());
	}
	
	@GetMapping
	@ResponseBody
	public List<PostDto> getAll(){
		return postService.getAll();		
	}
	
	@GetMapping("/user/{userId}")
	@ResponseBody
	public List<PostDto> getPostsByUserId(@PathVariable Long userId){	
		return postService.getAllByUserId(userId);
	}
	
	@GetMapping("/profile")
	@ResponseBody
	public List<PostDto> getPostsForLoggedUser(){
		return postService.getAllPostsLoggedUser();		
	}
	
	@PostMapping
	@ResponseBody
	public PostDto add(@RequestBody @Validated PostDto postDto){
		return toDto.convert(postService.save(postDto));
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public PostDto edit(@PathVariable Long id, @RequestBody @Validated PostDto dto){	
		if(!id.equals(dto.getId())) {
			throw new BadRequestException("Post id must be equal to id from URL") ;
		}
		return toDto.convert(postService.edit(dto));
	}
	

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Optional<Post> post = postService.getOne(id);
		if(post.isPresent()) {
			postService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
