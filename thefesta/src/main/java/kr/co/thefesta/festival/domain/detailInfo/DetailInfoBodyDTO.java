package kr.co.thefesta.festival.domain.detailInfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailInfoBodyDTO {
	private DetailInfoItemsDTO items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
