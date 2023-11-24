package kr.co.thefesta.festival.domain.detailImage;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DetailImageItemDTO {
	private String contentid;
	private String originimgurl;
	private String imgname;
	private String smallimageurl;
	private String cpyrhtDivCd;
	private String serialnum;
}
