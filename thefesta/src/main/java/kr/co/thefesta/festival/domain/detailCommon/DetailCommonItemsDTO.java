package kr.co.thefesta.festival.domain.detailCommon;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailCommonItemsDTO {
	private List<DetailCommonItemDTO> item;
}
