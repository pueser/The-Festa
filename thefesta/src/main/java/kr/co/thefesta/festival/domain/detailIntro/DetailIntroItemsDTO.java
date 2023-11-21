package kr.co.thefesta.festival.domain.detailIntro;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailIntroItemsDTO {
	private List<DetailIntroItemDTO> item;
}
