package letsTalk.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import letsTalk.exceptions.BadRequestException;
import letsTalk.model.User;
import letsTalk.model.UserRole;
import letsTalk.repository.UserRepository;
import letsTalk.security.IAuthenticationFacade;
import letsTalk.service.UserService;
import letsTalk.support.UserDtoToUser;
import letsTalk.web.dto.UserDto;
import letsTalk.web.dto.UserRegistrationDto;

@Service
public class JpaUserService implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private UserDtoToUser toUser;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Override
	public Optional<User> getOne(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public Optional<User> byUsername(String username) {	
		return userRepository.findFirstByUsername(username);
	}

	@Override
	public Page<User> getAll(int page) {
		return userRepository.findAll(PageRequest.of(page, 5));
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User register(UserRegistrationDto userRegistrationDto) {
		
		List <UserRole> roles = Arrays.asList(UserRole.values());
		
		User toRegister = toUser.convert(userRegistrationDto); 
		toRegister.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
		toRegister.setRole(roles.get(1)); //setting role for a new register user(user role)
		
		return userRepository.save(toRegister);
	}
	
	@Override
	public User edit(UserDto userEditDto) {
		return userRepository.save(toUser.convert(userEditDto));
	}
	
	@Override
	public User editProfile(UserDto userEditDto) {
		
		String username = authenticationFacade.currentUserNameSimple();
		User user = byUsername(username).get();
		
		if(!user.getId().equals(userEditDto.getId())) {
			throw new BadRequestException("Id from logged user needs to be equal to id of edited user");
		}
		
		return userRepository.save(toUser.convert(userEditDto));
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Page<User> search(String username, String firstName, String lastName, int pageNum) {
		
		if(username != null) {
			username = '%' + username + '%';
		}
		if(firstName != null) {
			firstName = '%' + firstName + '%';
		}
		if(lastName != null) {
			lastName = '%' + lastName + '%';
		}
		
		if(lastName == null && firstName == null && username == null) {
			return getAll(pageNum);
		}
		
		return userRepository.search(username, firstName, lastName, PageRequest.of(pageNum, 5));
	}
	
}
