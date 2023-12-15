package kr.co.thefesta.board.service;

import java.util.List;

import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.domain.ReplyDTO;

public interface IReplyService {

	public int register(ReplyDTO replyDto);
	public ReplyDTO read(int brno);
	public int modify(ReplyDTO replyDto);
	public int remove(int brno);
	public List<ReplyDTO> getList(Criteria cri, int bid);
	public int replyCntUpdate(int bid);
	public List<ReplyDTO> listAll();
}
