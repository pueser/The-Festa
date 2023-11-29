package kr.co.thefesta.food.persistence;

import java.util.List;

import kr.co.thefesta.food.domain.ItemDTO;

public interface IFoodDAO {
	public void insert(ItemDTO itemDto) throws Exception;
	public void delete() throws Exception;
	public List<ItemDTO> listAll() throws Exception;
}
