package kr.co.thefesta.board.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReplyDTO {

	private int brno;
	private String brcontent;
	private Timestamp brregist;
	private Timestamp bredit;
	private int bid;
	private String nickname;
	private String id;
}
