package kr.co.thefesta.festival.service;

import java.util.List;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.ReplyPageDTO;

public interface IFestivalReplyService {
	public int register(FestivalReplyDTO rDto) throws Exception;
	public boolean updateReply(FestivalReplyDTO rDto) throws Exception;
	public boolean deleteReply(int frno) throws Exception;
	public int getCountByContentid(String contentid);
	public List<FestivalReplyDTO> getListPage(Criteria cri, String contentid);
}
