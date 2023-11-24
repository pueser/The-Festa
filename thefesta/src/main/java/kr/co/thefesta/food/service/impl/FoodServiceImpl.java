package kr.co.thefesta.food.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.persistence.IFoodDAO;
import kr.co.thefesta.food.service.IFoodService;

@Service
public class FoodServiceImpl implements IFoodService {
	
	@Autowired
	private IFoodDAO fDao;

	@Override
	public void create(ItemDTO itemDto) throws Exception {
		fDao.insert(itemDto);
		
	}
	

}
