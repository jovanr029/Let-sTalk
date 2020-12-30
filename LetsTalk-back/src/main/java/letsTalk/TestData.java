package letsTalk;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import letsTalk.model.Comment;
import letsTalk.model.LikeIdEmbeddable;
import letsTalk.model.Like_Class;
import letsTalk.model.Post;
import letsTalk.model.User;
import letsTalk.model.UserRole;
import letsTalk.service.CommentService;
import letsTalk.service.LikeService;
import letsTalk.service.PostService;
import letsTalk.service.UserService;

@Component
public class TestData {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private LikeService likeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		List <UserRole> roles = Arrays.asList(UserRole.values());
		
		User user = new User();
		user.setUsername("jovan93");
		user.setFirstName("Jovan");
		user.setLastName("Radosavkic");
		user.setDateOfBirth(LocalDate.of(1993, 8, 30));
		user.setEmail("jradosavkic1@gmail.com");
		String encodedPass = passwordEncoder.encode("123");
		user.setPassword(encodedPass);
		user.setRole(roles.get(0));
		userService.save(user);
		
		User user1 = new User();
		user1.setUsername("maliPleonazam");
		user1.setFirstName("Marija");
		user1.setLastName("Marijanovic");
		user1.setEmail("marija@gmail.com");
		user1.setDateOfBirth(LocalDate.of(1996, 12, 28));
		String encodedPass1 = passwordEncoder.encode("456");
		user1.setPassword(encodedPass1);
		user1.setRole(roles.get(1));
		userService.save(user1);
		
		for (int i = 1; i <= 11; i++) {	
			User user3 = new User();
			user3.setUsername("user" + i);
			user3.setFirstName("First " + i);
			user3.setLastName("Last " + i);
			user3.setEmail("user"+i+"@mail.com");
			user3.setDateOfBirth(LocalDate.now().minusYears(20 + i));
			String encodedPass3 = passwordEncoder.encode("pass"+i);
			user3.setPassword(encodedPass3);
			user3.setRole(roles.get(1));			
			userService.save(user3);			
		}
					
		Post post1 = new Post();
		post1.setTitle("Reservoir dogs");
		post1.setContent("When a simple jewelry heist goes horribly wrong,"
				+ " the surviving criminals begin to suspect that one of them is a police informant. "
				+ "Reservoir dogs is the best movie i have ever seen. ");
		post1.setUser(user1);
		postService.save(post1);
		
		Post post2 = new Post();
		post2.setTitle("Serbia");
		post2.setContent("Serbia’s enchanting landscapes are a true feast for the eyes and soul."
				+ " Whichever region of Serbia you choose to visit,"
				+ " you are guaranteed to leave with lasting impressions of its magical scenery and unrivalled experiences.");
		post2.setUser(user);
		postService.save(post2);
		
		Post post3 = new Post();
		post3.setTitle("Today");
		post3.setContent("Today is a beautiful day");
		post3.setUser(user);
		postService.save(post3);
		
		Comment comment = new Comment();
		comment.setUser(user1);
		comment.setPost(post3);
		comment.setText("I don't think so");
		commentService.save(comment);
		
		Comment comment1 = new Comment();
		comment1.setUser(user);
		comment1.setPost(post1);
		comment1.setText("Fight club is better");
		commentService.save(comment1);
		
		Comment comment2 = new Comment();
		comment2.setUser(user1);
		comment2.setPost(post1);
		comment2.setText("You wish");
		commentService.save(comment2);	
		
		Like_Class like1 = new Like_Class();
		like1.setId(new LikeIdEmbeddable(user, post1));
		like1.setUser(user);
		like1.setPost(post1);
		likeService.save(like1);

		Like_Class like2 = new Like_Class();
		like2.setId(new LikeIdEmbeddable(user1, post1));
		like2.setUser(user1);
		like2.setPost(post1);
		likeService.save(like2);
		
		Like_Class like3 = new Like_Class();
		like3.setId(new LikeIdEmbeddable(user, post2));
		like3.setUser(user);
		like3.setPost(post2);
		likeService.save(like3);
		
		Like_Class like4 = new Like_Class();
		like4.setId(new LikeIdEmbeddable(user1, post2));
		like4.setUser(user);
		like4.setPost(post2);
		likeService.save(like4);
		
	}
	
	
}
