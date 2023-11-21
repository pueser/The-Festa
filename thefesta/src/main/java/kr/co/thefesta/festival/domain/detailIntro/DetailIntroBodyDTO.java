package kr.co.thefesta.festival.domain.detailIntro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailIntroBodyDTO {
	private DetailIntroItemsDTO items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
