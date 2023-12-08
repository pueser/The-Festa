package kr.co.thefesta.admin.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AdminDTO {
	
	//회원 아이디
	private String id;
	//회원 상태코드
	private String statecode;
	//회원 신고누적갯수
	private Integer reportnum;
	//회원 마지막 접속일
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "GMT+9")
	private Date finalaccess;
	//회원 총 신고누적갯수
	private Integer totalreportnum;
	//
	private String newStatecode;

	
	
}
