package kr.co.thefesta.festival.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FestivalDTO {
	private String contentid;
	private String title;
	private String eventstartdate;
	private String eventenddate;
	private String addr1;
	private String eventintro;
	private String eventtext;
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
	private int acode;
	private int scode;
	private double mapx;
	private double mapy;
	
}
