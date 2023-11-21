package kr.co.thefesta.festival.domain.detailCommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailCommonResponseDTO {
	private DetailCommonHeaderDTO header;
	private DetailCommonBodyDTO body;
}
