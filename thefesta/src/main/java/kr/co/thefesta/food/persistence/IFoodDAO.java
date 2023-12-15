package kr.co.thefesta.food.persistence;

import java.util.List;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;

public interface IFoodDAO {
	public void insert(ItemDTO itemDto) throws Exception;
	public void delete() throws Exception;
	public List<FestivalDTO> selectFestaId() throws Exception;
	public List<RecommendDTO> listAll(String contentid) throws Exception;
	public List<RecommendDTO> listAllUser(UserDTO userDto) throws Exception;
	public ItemDTO read(String contentid) throws Exception;
	public AreacodeDTO selectArea(String contentid) throws Exception;
	public void insertLike(LikeDTO likeDto) throws Exception;
	public void delete(LikeDTO likeDto) throws Exception;
	
	
}
