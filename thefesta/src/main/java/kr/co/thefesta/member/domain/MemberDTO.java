package kr.co.thefesta.member.domain;

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
public class MemberDTO {

	private String id;
	private String password;
	private String nickname;
	private String profileImg;
	private String agreement;
	private Date joindate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "GMT+9")
	private Date finalaccess;
	private Date withdrawdate;
	private Integer reportnum;
	private String statecode;
	private Date updatedate;
	
	private String resetPassword;
}
