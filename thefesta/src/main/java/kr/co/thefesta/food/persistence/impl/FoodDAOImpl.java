package kr.co.thefesta.food.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.persistence.IFoodDAO;

@Repository
public class FoodDAOImpl implements IFoodDAO {

	@Autowired
	private SqlSession session;

	@Override
	public void insert(ItemDTO itemDto) throws Exception {
		session.insert("FoodMapper.insert", itemDto);
		
	}

	
}
