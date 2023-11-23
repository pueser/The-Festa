package kr.co.thefesta.board.domain;

import lombok.Data;

@Data
public class BoardImageDTO {

	// 게시글 이미지 id
	private String bfileuuid;
	// 게시글 파일 이름
	private String bfileName;
	// 게시글 번호
	private int bid;
	// 업로드패스
	private String uploadpath;
	// 파일타입
	private char bfiletype;
	
}
