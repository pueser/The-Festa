package kr.co.thefesta.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.domain.ReplyDTO;
import kr.co.thefesta.board.mapper.BoardReplyMapper;
import kr.co.thefesta.board.service.IReplyService;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements IReplyService {
	@Autowired
	private BoardReplyMapper ReplyMapper;
	
	@Override
	public int register(ReplyDTO replyDto) {
		log.info("Reply register ............... " + replyDto);
		
		return ReplyMapper.insert(replyDto);
	}

	@Override
	public ReplyDTO read(int brno) {
		log.info("Reply read ............... " + brno);
		return ReplyMapper.read(brno);
	}

	@Override
	public int modify(ReplyDTO replyDto) {
		log.info("Reply modify ............... " + replyDto);
		return ReplyMapper.update(replyDto);
	}

	@Override
	public int remove(int brno) {
		log.info("Reply remove ............... " + brno);
		return ReplyMapper.delete(brno);
	}

	@Override
	public List<ReplyDTO> getList(Criteria cri, int bid) {
		log.info("Reply getList ............... " + bid);
		return ReplyMapper.getListWithPaging(cri, bid);
	}

	@Override
	public int replyCntUpdate(int bid) {
		log.info("replyCntUpdate ......" + bid);
		return ReplyMapper.replyCntUpdate(bid);
	}

}

