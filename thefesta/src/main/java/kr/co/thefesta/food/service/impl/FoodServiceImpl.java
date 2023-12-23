package kr.co.thefesta.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.NonUserDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;
import kr.co.thefesta.food.domain.api.ItemDTO;
import kr.co.thefesta.food.persistence.IFoodDAO;
import kr.co.thefesta.food.service.IFoodService;


@Service("foodService")
public class FoodServiceImpl implements IFoodService {
	
	@Autowired
	private IFoodDAO fDao;
	
	public FoodServiceImpl(IFoodDAO fDao) {
		this.fDao = fDao;
	}

	@Override
	public void create(ItemDTO itemDto) throws Exception {
		fDao.insert(itemDto);
	}

	@Override
	public void delete() throws Exception {
		fDao.delete();
	}
	
	@Override
	public void update(ItemDTO itemDto) throws Exception {
		fDao.update(itemDto);
	}
	
	@Override
	public List<FestivalDTO> selectFestaId() throws Exception {
		List<FestivalDTO> festaDto = fDao.selectFestaId();
		return festaDto;
	}
	
	@Override
	public int listCnt(String contentid) throws Exception {
		return fDao.listCnt(contentid);
	}

	@Override
	public List<RecommendDTO> listAll(NonUserDTO nonUserDto) throws Exception {
		List<RecommendDTO> recDto = fDao.listAll(nonUserDto);
		return recDto;
	}
	
	@Override
	public List<RecommendDTO> listAllUser(UserDTO userDto) throws Exception {
		List<RecommendDTO> recDto = fDao.listAllUser(userDto);
		return recDto;
	}
	
	@Override
	public ItemDTO read(String contentid) throws Exception {
		return fDao.read(contentid);
	}


	@Override
	public AreacodeDTO selectArea(String contentid) throws Exception {
		AreacodeDTO areaDto = fDao.selectArea(contentid);
		return areaDto;
	}

	@Override
	public void insertLike(LikeDTO likeDto) throws Exception {
		fDao.insertLike(likeDto);
	}

	@Override
	public void deleteLike(LikeDTO likeDto) throws Exception {
		fDao.deleteLike(likeDto);
	}

	@Override
	public List<LikeDTO> userLikeList(String id) throws Exception {
		return fDao.userLikeList(id);
	}

}