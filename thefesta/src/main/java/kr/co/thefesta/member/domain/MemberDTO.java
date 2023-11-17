package kr.co.thefesta.member.domain;

import java.util.Date;

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
	private Date finalaccess;
	private Date withdrawdate;
	private Integer reportnum;
	
}
