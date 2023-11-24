package kr.co.thefesta.festival.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FestivalImageDTO {
	private int ffileno;
	private String contentid;
	private String originimgurl;
	private String smallimageurl;
}
