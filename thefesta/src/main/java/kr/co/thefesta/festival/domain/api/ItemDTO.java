package kr.co.thefesta.festival.domain.api;

import java.util.Date;
import java.util.List;

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
	private String eventstartdate;
	private String eventenddate;
	private String addr1;
	private String infoname;
	private String infotext;
	private String homepage;
	private String agelimit;
	private String sponsor1;
	private String sponsor1tel;
	private String sponsor2;
	private String sponsor2tel;
	private String usetimefestival;
	private String playtime;
	private String firstimage;
	private String firstimage2;
	private String areacode;
	private String sigungucode;
	private String mapx;
	private String mapy;
    private String cat2;
	private String originimgurl;
	private String smallimageurl;
	private String serialnum;
//    private String eventintro;
//    private String eventtext;
}
