package kr.co.thefesta.festival.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LikeDTO {
	private int lno;
	private String contentid;
	private String id;
}
