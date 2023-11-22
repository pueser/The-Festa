package kr.co.thefesta.board.mapper;

import java.util.List;

import kr.co.thefesta.board.domain.BoardImageDTO;

public interface BoardImageMapper {
	public void insert(BoardImageDTO BoardImageDto);
	public void delete(String bfileuuid);
	public List<BoardImageDTO> findByBid(int bid); 
}
