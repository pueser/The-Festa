package kr.co.thefesta.festival.domain.areacode;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AreaCodeItemDTO {
	private int rnum;
	private int code;
	private String name;
}
