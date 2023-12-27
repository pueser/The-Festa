package kr.co.thefesta.board.service;

import java.util.List;

import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.board.domain.Criteria;

public interface IBoardService {

	public void register(BoardDTO bDto) throws Exception;
	public BoardDTO read(int bid) throws Exception;
	public boolean modify(BoardDTO bDto) throws Exception;
	public boolean remove(int bid) throws Exception;
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
	public void increaseViewCnt(int bid) throws Exception;
	public List<BoardDTO> listGet() throws Exception;
	public List<BoardDTO> userBoard(String id) throws Exception;
	
	
}
