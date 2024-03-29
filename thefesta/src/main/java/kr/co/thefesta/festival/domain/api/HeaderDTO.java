package kr.co.thefesta.festival.domain.api;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class HeaderDTO {

	private String resultCode;
	private String resultMsg;
}
