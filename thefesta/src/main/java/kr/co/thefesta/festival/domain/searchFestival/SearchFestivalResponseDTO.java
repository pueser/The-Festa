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
public class SearchFestivalResponseDTO {
	
	private SearchFestivalHeaderDTO header;
	private SearchFestivalBodyDTO body;
}
