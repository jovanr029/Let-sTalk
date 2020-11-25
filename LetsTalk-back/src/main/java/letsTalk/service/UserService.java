package letsTalk.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import letsTalk.model.User;
import letsTalk.web.dto.UserDto;
import letsTalk.web.dto.UserRegistrationDto;

public interface UserService {
	
	Optional<User> getOne(Long id);
	Optional<User> byUsername(String username);
	Page<User> getAll(int pageNum);
	Page<User> search(
			@Param("username") String username,
			@Param("firstName") String firstName,
			@Param("lastname") String lastName,
			int pageNum);
	User save (User user);
	User register(UserRegistrationDto userRegistrationDto);
	User edit(UserDto userEditDto);
	User editProfile(UserDto userEditDto);
	void delete(Long id);
	
}
