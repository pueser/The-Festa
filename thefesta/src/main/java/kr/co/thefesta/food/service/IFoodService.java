package kr.co.thefesta.food.service;

import org.springframework.stereotype.Service;

import kr.co.thefesta.food.domain.ItemDTO;

@Service
public interface IFoodService {
	
	public void create(ItemDTO itemDto) throws Exception;
	public void delete() throws Exception;
	

}
