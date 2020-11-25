package letsTalk.model;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Like_Class  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long likeId;
	@ManyToOne(fetch = FetchType.EAGER)
	private Post post;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
}
