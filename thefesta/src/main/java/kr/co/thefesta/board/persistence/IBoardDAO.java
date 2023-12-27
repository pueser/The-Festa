package kr.co.thefesta.board.persistence;

import java.util.List;

import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.board.domain.Criteria;

public interface IBoardDAO {

	public void create(BoardDTO bDto) throws Exception;
	public BoardDTO read(int bid) throws Exception;
	public int update(BoardDTO bDto) throws Exception;
	public int delete(int bid) throws Exception;
//	public List<BoardDTO> listAll() throws Exception;
	
	public List<BoardDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
	public void increaseViewCnt(int bid) throws Exception;
	public List<BoardDTO> listGet() throws Exception;
	public List<BoardDTO> userBoard(String id) throws Exception;
	
}
