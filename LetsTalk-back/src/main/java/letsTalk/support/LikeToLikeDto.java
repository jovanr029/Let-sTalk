package letsTalk.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import letsTalk.model.Like_Class;
import letsTalk.web.dto.LikeDto;

@Component
public class LikeToLikeDto implements Converter<Like_Class, LikeDto> {

	@Override
	public LikeDto convert(Like_Class source) {
		LikeDto likeDto = new LikeDto();
		likeDto.setPostId(source.getPost().getId());
		likeDto.setUser(source.getUser().getUsername());
		return likeDto;
	}
	
	public List<LikeDto> convert(List<Like_Class> source) {
		List<LikeDto> target = new ArrayList<>();
		for(Like_Class l : source) {
			target.add(convert(l));
		}
		return target;
	}

}
