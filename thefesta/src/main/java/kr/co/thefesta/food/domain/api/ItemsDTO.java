package kr.co.thefesta.food.domain.api;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemsDTO {

	private ArrayList<ItemDTO> item;
	
}
