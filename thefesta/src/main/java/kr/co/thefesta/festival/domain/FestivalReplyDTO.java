package kr.co.thefesta.festival.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class FestivalReplyDTO {
	private int frno;
	private String contentid;
	private String id;
	private String nickname;
	private String frcontent;
	private Date frregist;
	private Date fredit;
}
