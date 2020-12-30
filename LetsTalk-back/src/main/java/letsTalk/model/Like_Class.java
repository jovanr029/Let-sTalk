package letsTalk.model;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Like_Class  {
	
	@EmbeddedId
	private LikeIdEmbeddable id;	
	@ManyToOne(fetch = FetchType.EAGER)
	private Post post;
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
}
