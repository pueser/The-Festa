package kr.co.thefesta.festival.domain.detailImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailImageResponseDTO {
	private DetailImageHeaderDTO header;
	private DetailImageBodyDTO body;
}
