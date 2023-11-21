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
public class SearchFestivalHeaderDTO {

	private String resultCode;
	private String resultMsg;
}
