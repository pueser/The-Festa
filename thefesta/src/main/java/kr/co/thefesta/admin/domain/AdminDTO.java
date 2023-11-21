package kr.co.thefesta.admin.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminDTO {

	private Integer integer;
	private String string;
	private Date date;
	private int num;
	
}
