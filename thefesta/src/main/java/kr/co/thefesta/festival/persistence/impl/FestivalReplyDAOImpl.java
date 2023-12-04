package kr.co.thefesta.festival.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.ReplyPageDTO;
import kr.co.thefesta.festival.persistence.IFestivalDAO;
import kr.co.thefesta.festival.persistence.IFestivalReplyDAO;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class FestivalReplyDAOImpl implements IFestivalReplyDAO {
	@Autowired
	private SqlSession session;

	@Override
	public int register(FestivalReplyDTO rDto) throws Exception {
		return session.insert("FestivalReplyMapper.insertReply", rDto);
	}

	@Override
	public List<FestivalReplyDTO> getListWithPaging(@Param("cri") Criteria cri, @Param("contentid") String contentid) {
		Map<String, Object> params = new HashMap<>();
	    params.put("cri", cri);
	    params.put("contentid", contentid);
	    log.info("params : " + params);
	    
		return session.selectList("FestivalReplyMapper.getListWithPaging", params);
	}

	@Override
	public int getCountByContentid(String contentid) {
		return session.selectOne("FestivalReplyMapper.getCountByContentid", contentid);
	}

	@Override
	public int updateReply(FestivalReplyDTO rDto) throws Exception {
		return session.update("FestivalReplyMapper.updateReply", rDto);
	}

	@Override
	public int deleteReply(int frno) throws Exception {
		return session.update("FestivalReplyMapper.deleteReply", frno);
	}
}
