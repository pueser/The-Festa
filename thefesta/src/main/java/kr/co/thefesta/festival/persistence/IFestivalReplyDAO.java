package kr.co.thefesta.festival.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;

public interface IFestivalReplyDAO {
	public int register(FestivalReplyDTO rDto) throws Exception;
	public int updateReply(FestivalReplyDTO rDto) throws Exception;
	public int deleteReply(int frno) throws Exception;
	public List<FestivalReplyDTO> getListWithPaging(@Param("cri") Criteria cri, @Param("contentid") String contentid);
	
	public int getCountByContentid(String contentid);
}
