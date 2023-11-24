package kr.co.thefesta.festival.domain.detailImage;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailImageItemsDTO {
	private List<DetailImageItemDTO> item;
}
