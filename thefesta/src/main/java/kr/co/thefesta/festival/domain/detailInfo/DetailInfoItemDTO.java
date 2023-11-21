package kr.co.thefesta.festival.domain.detailInfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailInfoItemDTO {
	private String contentid;
	private String contenttypeid;
	private String serialnum;
	private String infoname;
	private String infotext;
	private String fldgubun;
}
