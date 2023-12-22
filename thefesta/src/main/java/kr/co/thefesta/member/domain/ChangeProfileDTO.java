package kr.co.thefesta.member.domain;

import lombok.Data;

@Data
public class ChangeProfileDTO {
	private String fileName;
	private String uploadPath;
	private String uuid;
	private boolean image;
}
