package letsTalk.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {
	
	private Long id;
	@NotBlank
	private String username;
	private String firstName;
	private String lastName;
	@NotEmpty
	@Email
	private String email;
	private LocalDate dateOfBirth;

}
