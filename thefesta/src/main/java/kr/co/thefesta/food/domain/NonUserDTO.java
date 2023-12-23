package kr.co.thefesta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class NonUserDTO {
	
	private String contentid;
	private Criteria cri;
	
	public int getPageNum() {
		return cri.getPageNum();
	}
	
	public int getAmount() {
		return cri.getAmount();
	}

}