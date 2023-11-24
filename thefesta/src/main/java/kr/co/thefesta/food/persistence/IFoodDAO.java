package kr.co.thefesta.food.persistence;

import kr.co.thefesta.food.domain.ItemDTO;

public interface IFoodDAO {
	public void insert(ItemDTO itemDto) throws Exception;
}
