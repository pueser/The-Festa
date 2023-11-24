package kr.co.thefesta.festival.domain.areacode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AreaCodeBodyDTO {
	private AreaCodeItemsDTO items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}
