package kr.co.thefesta.festival.domain.areacode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AreaCodeResponseDTO {
	private AreaCodeHeaderDTO header;
	private AreaCodeBodyDTO body;
}
