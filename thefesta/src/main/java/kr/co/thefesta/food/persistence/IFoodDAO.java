package kr.co.thefesta.food.persistence;

import kr.co.thefesta.food.domain.ItemDTO;

public interface IFoodDAO {
	public void insert(ItemDTO itemDto) throws Exception;
	public void delete() throws Exception;
	
//	List<Map<String, Object>> selectFood();
//	void updateFood(Map<String, Object> food);
	
}
