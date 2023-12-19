package kr.co.thefesta.food.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;

@Service
public interface IFoodService {
	
	public void create(ItemDTO itemDto) throws Exception;
	public void delete() throws Exception;
	public List<FestivalDTO> selectFestaId() throws Exception;
	public List<RecommendDTO> listAll(String contentid) throws Exception;
	public List<RecommendDTO> listAllUser(UserDTO userDto) throws Exception;
	public ItemDTO read(String contentid) throws Exception;
	public AreacodeDTO selectArea(String contentid) throws Exception;
	public void insert(LikeDTO likeDto) throws Exception;
	public void delete(LikeDTO likeDto) throws Exception;
	public List<LikeDTO> userLikeList(String id) throws Exception;

}
