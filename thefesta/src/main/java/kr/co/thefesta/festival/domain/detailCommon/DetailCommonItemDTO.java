package kr.co.thefesta.festival.domain.detailCommon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailCommonItemDTO {
	private String contentid;
	private String contenttypeid;
	private String title;
	private String createdtime;
	private String modifiedtime;
	private String tel;
	private String telname;
	private String homepage;
	private String booktour;
	private String firstimage;
	private String firstimage2;
	private String cpyrhtDivCd;
	private String areacode;
	private String sigungucode;
	private String addr1;
	private String addr2;
	private String zipcode;
	private double mapx;
	private double mapy;
	private int mlevel;
	private String overview;
}
