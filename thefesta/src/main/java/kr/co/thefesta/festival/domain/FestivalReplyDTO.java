package kr.co.thefesta.festival.domain;

import java.sql.Timestamp;

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
	private Timestamp frrgist;
	private Timestamp fredit;
}
