package kr.co.thefesta.food.persistence;

import java.util.List;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.NonUserDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;
import kr.co.thefesta.food.domain.api.ItemDTO;

public interface IFoodDAO {
	
	public void insert(ItemDTO itemDto) throws Exception;
	public void delete() throws Exception;
	public void update(ItemDTO itemDto) throws Exception;
	public List<FestivalDTO> selectFestaId() throws Exception;
	public int listCnt(String contentid) throws Exception;
	public List<RecommendDTO> listAll(NonUserDTO nonUserDto) throws Exception;
	public List<RecommendDTO> listAllUser(UserDTO userDto) throws Exception;
	public ItemDTO read(String contentid) throws Exception;
	public AreacodeDTO selectArea(String contentid) throws Exception;
	public void insertLike(LikeDTO likeDto) throws Exception;
	public void deleteLike(LikeDTO likeDto) throws Exception;
	public List<LikeDTO> userLikeList(String id) throws Exception;
	
}
