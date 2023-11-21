package kr.co.thefesta.festival.domain.detailInfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailInfoItemsDTO {
	private List<DetailInfoItemDTO> item;
}
