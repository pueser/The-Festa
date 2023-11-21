package kr.co.thefesta.map.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MapDTO {

	private Integer integer;
	private String string;
	private Date date;
	private int num;
	
}
