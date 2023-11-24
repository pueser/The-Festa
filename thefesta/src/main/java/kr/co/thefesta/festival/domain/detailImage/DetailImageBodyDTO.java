package kr.co.thefesta.festival.domain.detailImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailImageBodyDTO {
	private DetailImageItemsDTO items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
