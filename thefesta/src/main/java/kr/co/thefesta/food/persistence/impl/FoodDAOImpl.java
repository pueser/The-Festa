package kr.co.thefesta.food.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.food.domain.AreacodeDTO;
import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.domain.LikeDTO;
import kr.co.thefesta.food.domain.RecommendDTO;
import kr.co.thefesta.food.domain.UserDTO;
import kr.co.thefesta.food.persistence.IFoodDAO;
import lombok.extern.log4j.Log4j;

@Repository
@Log4j
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
	public List<RecommendDTO> listAll(String contentid) throws Exception {
		return session.selectList("FoodMapper.select", contentid);
	}
	
	@Override
	public List<RecommendDTO> listAllUser(UserDTO userDto) throws Exception {
		return session.selectList("FoodMapper.selectUser", userDto);
	} 

	@Override
	public ItemDTO read(String contentid) throws Exception {
		return session.selectOne("FoodMapper.read", contentid);
	}

	@Override
	public List<FestivalDTO> selectFestaId() throws Exception {
		return session.selectList("FoodMapper.selectFestaid");
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
	public void delete(LikeDTO likeDto) throws Exception {
		session.delete("FoodMapper.deleteLike", likeDto);
	}

	
	
}
