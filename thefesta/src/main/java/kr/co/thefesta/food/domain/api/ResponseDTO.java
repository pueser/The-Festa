package kr.co.thefesta.food.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ResponseDTO {

	private HeaderDTO header;
	private BodyDTO body;
	
}
