package kr.co.thefesta.food.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ItemDTO {
	
	private String contentid;
	private String title;
	private String addr1;
	private String infocenterfood;
	private String firstmenu;
	private String treatmenu;
	private String opentimefood;
	private String restdatefood;
	private String parkingfood;
	private String overview;
	private String firstimage;
	private String firstimage2;
	private String mapx;
	private String mapy;
	private String areacode;
	private String sigungucode;
	private String cat3;
	
}
