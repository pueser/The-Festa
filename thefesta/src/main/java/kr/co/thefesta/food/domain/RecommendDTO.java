package kr.co.thefesta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class RecommendDTO {

	private String contentid;
	private String title;
	private String addr1;
	private String firstimage2;
	private int	likeCnt;
}
