package letsTalk.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter @Setter
public class LikeIdEmbeddable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long user;
	private Long post;
	
	public LikeIdEmbeddable() {
	}

	public LikeIdEmbeddable(Long user, Long post) {
		this.user = user;
		this.post = post;
	}
	
	public LikeIdEmbeddable(User user, Post post) {
		this.user = user.getId();
		this.post = post.getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((post == null) ? 0 : post.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LikeIdEmbeddable other = (LikeIdEmbeddable) obj;
		if (post == null) {
			if (other.post != null)
				return false;
		} else if (!post.equals(other.post))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
