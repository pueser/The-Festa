package kr.co.thefesta.festival.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {

	private int replyCnt; 
	private List<FestivalReplyDTO> list;
}
