package kr.co.thefesta.food.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.food.domain.ItemDTO;
import kr.co.thefesta.food.persistence.IFoodDAO;
import kr.co.thefesta.food.service.IFoodService;
import lombok.extern.log4j.Log4j;


@Service("foodService")
@Log4j
public class FoodServiceImpl implements IFoodService {
	
	@Autowired
	private IFoodDAO fDao;
	
//	private final FoodDAO foodDao;
	
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
	public List<ItemDTO> listAll() throws Exception {
		List<ItemDTO> itemDto = fDao.listAll();
//		log.info("listAll data : " + itemDto);
		return itemDto;
	}

}
