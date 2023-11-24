package kr.co.thefesta.festival.domain.areacode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AreaCodeHeaderDTO {
	private String resultCode;
	private String resultMsg;
}
