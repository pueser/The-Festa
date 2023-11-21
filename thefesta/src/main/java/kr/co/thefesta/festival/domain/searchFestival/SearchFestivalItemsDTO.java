package kr.co.thefesta.festival.domain.searchFestival;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SearchFestivalItemsDTO {

	private List<SearchFestivalItemDTO> item;
}
