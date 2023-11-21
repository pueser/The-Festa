package kr.co.thefesta.festival.domain.searchFestival;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SearchFestivalBodyDTO {

	private SearchFestivalItemsDTO items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
