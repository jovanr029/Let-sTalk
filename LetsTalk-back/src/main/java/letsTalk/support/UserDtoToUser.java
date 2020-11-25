package letsTalk.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.User;
import letsTalk.service.UserService;
import letsTalk.web.dto.UserDto;

@Component
public class UserDtoToUser implements Converter<UserDto, User> {
	
	@Autowired
	private UserService userService;

	@Override
	public User convert(UserDto source) {
		
		User target = null;
		if(source.getId() != null) {
			target = userService.getOne(source.getId()).get();
		}
		
		if(target == null) { 
			target = new User();
		}
				
		target.setId(source.getId());
		target.setUsername(source.getUsername());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setDateOfBirth(source.getDateOfBirth());
		return target;
	}

}
