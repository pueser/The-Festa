package kr.co.thefesta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDTO {
	
	private String contentid;
	private String id;
	private Criteria cri;
	
	public int getPageNum() {
		return cri.getPageNum();
	}
	
	public int getAmount() {
		return cri.getAmount();
	}

}
