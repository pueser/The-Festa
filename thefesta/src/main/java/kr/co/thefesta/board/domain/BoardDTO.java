package kr.co.thefesta.board.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BoardDTO {
	
	private Integer bno;
	private int bid;
	private String btitle;
	private String bcontent;
	private Timestamp bregdate;
	private Timestamp bedit;
	private String nickname;
	private String id;
	private int viewcnt;
	private int replycnt;
	private List<BoardImageDTO> attachList;
}
