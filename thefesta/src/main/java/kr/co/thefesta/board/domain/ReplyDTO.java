package kr.co.thefesta.board.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class ReplyDTO {

	// 댓글 번호
	private int brno;
	// 댓글 내용
	private String brcontent;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
	private Date brregist;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm:ss")
	private Date bredit;
	
	// 게시글 번호
	private int bid;
	// 댓글 작성자
	private String nickname;
	// 댓글 작성자 id
	private String id;
}
