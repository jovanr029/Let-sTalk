package letsTalk.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import letsTalk.exceptions.BadRequestException;
import letsTalk.exceptions.ResourceNotFoundException;
import letsTalk.model.User;
import letsTalk.security.IAuthenticationFacade;
import letsTalk.security.TokenUtils;
import letsTalk.service.UserService;
import letsTalk.support.UserToUserDto;
import letsTalk.web.dto.LoginDto;
import letsTalk.web.dto.UserDto;
import letsTalk.web.dto.UserRegistrationDto;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserToUserDto toDto;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenUtils tokenUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private IAuthenticationFacade authenticationFacade;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDto>> getAll(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum){	
		
		Page<User> users = userService.search(username, firstName, lastName, pageNum);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(users.getTotalPages()) );
		
		return new ResponseEntity<>(toDto.convert(users.getContent()), headers, HttpStatus.OK);	
	}
	
	/*
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<UserDto> getAll(
			@RequestParam(required = false) String username,
			@RequestParam(required = false) String firstName,
			@RequestParam(required = false) String lastName,
			@RequestParam(value="pageNum", defaultValue="0") int pageNum,
			HttpServletResponse response){	
		
		Page<User> users = userService.search(username, firstName, lastName, pageNum);
		response.setHeader("Total-Pages", Integer.toString(users.getTotalPages()));
		
		return toDto.convert(users.getContent());
	}
	*/
	@GetMapping("/{id}")
	@ResponseBody
	public UserDto getOne(@PathVariable Long id) {	
		Optional<User> user = userService.getOne(id);
		
		if(user.isPresent()) {
			return toDto.convert(user.get());
		}else {
			throw new ResourceNotFoundException("No user with id:" + id);
		}
	}
	
	@GetMapping("/profile")
	@ResponseBody
	public UserDto getMyProfile(){	
		//Getting logged in user
		String username = authenticationFacade.currentUserNameSimple();
		Optional<User> user = userService.byUsername(username);
		
		if(user.isPresent()) {
			return toDto.convert(user.get());
		}else {
			throw new ResourceNotFoundException("No user with that username");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto dto) {
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(dto.getUsername());
			return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<UserDto> registration(@RequestBody @Validated UserRegistrationDto reqBody){
		
		if(reqBody.getId() != null || !reqBody.getPassword().equals(reqBody.getPasswordConfirm())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(toDto.convert(userService.register(reqBody)), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public UserDto edit(@PathVariable Long id, @RequestBody @Validated UserDto reqBody){
		
		if(!id.equals(reqBody.getId())) {
			throw new BadRequestException("Id from URL is difrent from User id");
		}
		
		return toDto.convert(userService.edit(reqBody));		
	}
	
	@PutMapping("/profile/edit")
	@ResponseBody
	public UserDto editProfile( @RequestBody @Validated UserDto reqBody){
		return toDto.convert(userService.editProfile(reqBody));		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		Optional<User> user = userService.getOne(id);
		
		if(!user.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
