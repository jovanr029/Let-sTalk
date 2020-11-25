package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.User;
import letsTalk.web.dto.UserDto;

@Component
public class UserToUserDto implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User source) {
		
		UserDto target = new UserDto();
		target.setId(source.getId());
		target.setUsername(source.getUsername());
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setEmail(source.getEmail());
		target.setDateOfBirth(source.getDateOfBirth());
		
		return target;
	}
	
	public List<UserDto> convert(List<User> source){
		List<UserDto> target = new ArrayList<UserDto>();
		
		for(User u : source) {
			UserDto dto = convert(u);
			target.add(dto);			
		}
		return target;
	}

}
