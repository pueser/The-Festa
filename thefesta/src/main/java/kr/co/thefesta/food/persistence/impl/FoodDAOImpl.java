package kr.co.thefesta.food.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.NonUserDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;
import kr.co.thefesta.food.domain.api.ItemDTO;
import kr.co.thefesta.food.persistence.IFoodDAO;

@Repository
public class FoodDAOImpl implements IFoodDAO {

	@Autowired
	private SqlSession session;

	@Override
	public void insert(ItemDTO itemDto) throws Exception {
		session.insert("FoodMapper.insert", itemDto);
	}

	@Override
	public void delete() throws Exception {
		session.delete("FoodMapper.delete");
	}
	
	@Override
	public void update(ItemDTO itemDto) throws Exception {
		session.update("FoodMapper.update", itemDto);
	}
	
	@Override
	public List<FestivalDTO> selectFestaId() throws Exception {
		return session.selectList("FoodMapper.selectFestaid");
	}
	
	@Override
	public int listCnt(String contentid) throws Exception {
		return session.selectOne("FoodMapper.listCnt",contentid);
	}

	@Override
	public List<RecommendDTO> listAll(NonUserDTO nonUserDto) throws Exception {
		return session.selectList("FoodMapper.recommend", nonUserDto);
	}
	
	@Override
	public List<RecommendDTO> listAllUser(UserDTO userDto) throws Exception {
		return session.selectList("FoodMapper.recommendUser", userDto);
	} 

	@Override
	public ItemDTO read(String contentid) throws Exception {
		return session.selectOne("FoodMapper.read", contentid);
	}

	@Override
	public AreacodeDTO selectArea(String contentid) throws Exception {
		return session.selectOne("FoodMapper.selectAreacode", contentid);
	}

	@Override
	public void insertLike(LikeDTO likeDto) throws Exception {
		session.insert("FoodMapper.insertLike", likeDto);
	}

	@Override
	public void deleteLike(LikeDTO likeDto) throws Exception {
		session.delete("FoodMapper.deleteLike", likeDto);
	}

	@Override
	public List<LikeDTO> userLikeList(String id) throws Exception {
		return session.selectList("FoodMapper.userLikeList", id);
	}

}