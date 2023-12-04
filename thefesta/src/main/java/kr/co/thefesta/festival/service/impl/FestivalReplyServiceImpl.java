package kr.co.thefesta.festival.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.ReplyPageDTO;
import kr.co.thefesta.festival.persistence.IFestivalDAO;
import kr.co.thefesta.festival.persistence.IFestivalReplyDAO;
import kr.co.thefesta.festival.service.IFestivalReplyService;
import kr.co.thefesta.festival.service.IFestivalService;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FestivalReplyServiceImpl implements IFestivalReplyService {
	
	@Autowired
	private IFestivalReplyDAO frDao;

	@Override
	public int register(FestivalReplyDTO rDto) throws Exception {
		return frDao.register(rDto);
	}

	@Override
	public int getCountByContentid(String contentid) {
		return frDao.getCountByContentid(contentid);
	}

	@Override
	public List<FestivalReplyDTO> getListPage(Criteria cri, String contentid) {
		return frDao.getListWithPaging(cri, contentid);
	}

	@Override
	public boolean updateReply(FestivalReplyDTO rDto) throws Exception {
		return frDao.updateReply(rDto) == 1;
	}

	@Override
	public boolean deleteReply(int frno) throws Exception {
		return frDao.deleteReply(frno) == 1;
	}

}
