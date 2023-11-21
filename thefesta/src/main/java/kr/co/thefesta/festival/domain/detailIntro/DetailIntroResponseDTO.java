package kr.co.thefesta.festival.domain.detailIntro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailIntroResponseDTO {
	private DetailIntroHeaderDTO header;
	private DetailIntroBodyDTO body;
}
