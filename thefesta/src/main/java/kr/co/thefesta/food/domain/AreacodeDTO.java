package kr.co.thefesta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AreacodeDTO {

	private String title;
	private int acode;
	private int scode;
	private String aname;
	private String sname;
	
}
