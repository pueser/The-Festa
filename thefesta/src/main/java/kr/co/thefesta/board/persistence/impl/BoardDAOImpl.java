package kr.co.thefesta.board.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.persistence.IBoardDAO;

@Repository
public class BoardDAOImpl implements IBoardDAO {
	@Autowired
	private SqlSession session;
	
	@Override
	public void create(BoardDTO bDto) throws Exception {
		session.insert("BoardMapper.create", bDto);
	}

	@Override
	public BoardDTO read(int bid) throws Exception {
		return session.selectOne("BoardMapper.read", bid);
	}

	@Override
	public int update(BoardDTO bDto) throws Exception {
		return session.update("BoardMapper.update", bDto);
	}

	@Override
	public int delete(int bid) throws Exception {
		return session.delete("BoardMapper.delete", bid);
	}

//	@Override
//	public List<BoardDTO> listAll() throws Exception {
//		return session.selectList("BoardMapper.listAll");
//	}

	@Override
	public List<BoardDTO> listAll(Criteria cri) throws Exception {
		return session.selectList("BoardMapper.getListWithPaging", cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return session.selectOne("BoardMapper.getTotalCnt", cri);
	}

	@Override
	public void increaseViewCnt(int bid) throws Exception {
		session.update("BoardMapper.increaseViewCnt", bid);
		
	}
}