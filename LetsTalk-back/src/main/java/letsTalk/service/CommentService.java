package letsTalk.service;

import java.util.List;
import java.util.Optional;

import letsTalk.model.Comment;
import letsTalk.web.dto.CommentDto;

public interface CommentService {
	
	Optional<Comment> getOne(Long id);
	List<Comment> getAll();
	List<CommentDto> getAllForPost(Long idPost);
	Comment save(Comment comment);
	Comment save(CommentDto commentDto);
	void delete(Long id);
	
}
