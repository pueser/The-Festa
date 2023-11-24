package kr.co.thefesta.festival.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AreaCodeDTO {
	private int ano;
	private int acode;
	private String aname;
	private int scode;
	private String sname;
}
