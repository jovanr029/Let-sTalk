package letsTalk.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import letsTalk.exceptions.BadRequestException;
import letsTalk.exceptions.ResourceNotFoundException;
import letsTalk.model.Comment;
import letsTalk.repository.CommentRepository;
import letsTalk.service.CommentService;
import letsTalk.service.PostService;
import letsTalk.support.CommentDtoToComment;
import letsTalk.support.CommentToCommentDto;
import letsTalk.web.dto.CommentDto;

@Service
public class JpaCommentService implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentToCommentDto toDto;
	
	@Autowired
	private CommentDtoToComment toComment;
	
	@Override
	public Optional<Comment> getOne(Long id) {
		return commentRepository.findById(id);
	}

	@Override
	public List<Comment> getAll() {	
		return commentRepository.findAll();
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void delete(Long id) {
		commentRepository.deleteById(id);
	}

	@Override
	public List<CommentDto> getAllForPost(Long idPost) {
		if(!postService.getOne(idPost).isPresent()) {
			throw new ResourceNotFoundException("No post with id: " + idPost);
		}
		return toDto.convert(commentRepository.findByPostId(idPost));
	}

	@Override
	public Comment save(CommentDto commentDto) {
		if(commentDto.getId() != null) {
			throw new BadRequestException("Id in comment must be null");
		}
		
		return commentRepository.save(toComment.convert(commentDto));
	}

}
