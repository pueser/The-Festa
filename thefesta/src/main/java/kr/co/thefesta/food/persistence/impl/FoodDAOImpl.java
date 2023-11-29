package kr.co.thefesta.food.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.food.domain.ItemDTO;
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
	public List<ItemDTO> listAll() throws Exception {
		return session.selectList("FoodMapper.select");
	}
}
