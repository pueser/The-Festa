package kr.co.thefesta.board.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BoardDTO {
	
	// 게시판 분류 번호
	private Integer bno;
	// 게시글 번호
	private int bid;
	// 게시글 제목
	private String btitle;
	// 게시글 내용
	private String bcontent;
	
	// 게시글 등록일자
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "GMT+9")
	private Date bregist;
	// 게시글 수정일자
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "GMT+9")
	private Date bedit;
	
	// 게시글 작성자
	private String nickname;
	// 게시글 작성자 id
	private String id;
	
	// 게시글 조회수
	private int bviewcnt;
	// 게시글 댓글수
	private int breplycnt;
	
	// 게시글 이미지 리스트
	private List<BoardImageDTO> attachList;
	
	// 게시글 상태코드
	private String bstatecode;
}
