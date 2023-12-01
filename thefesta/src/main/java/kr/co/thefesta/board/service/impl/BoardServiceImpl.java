package kr.co.thefesta.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.mapper.BoardImageMapper;
import kr.co.thefesta.board.persistence.IBoardDAO;
import kr.co.thefesta.board.service.IBoardService;

@Service
public class BoardServiceImpl implements IBoardService {

	@Autowired
	private IBoardDAO bDao;
	
	@Autowired
	private BoardImageMapper BoardImageMapper;
	
	@Transactional
	@Override
	public void register(BoardDTO bDto) throws Exception {
		bDao.create(bDto);
		
		if(bDto.getAttachList() == null || bDto.getAttachList().size() <= 0) {
			return;
		}
		bDto.getAttachList().forEach(attach -> {
			attach.setBid(bDto.getBid());
			BoardImageMapper.insert(attach);
			
		});
	}

	@Override
	public BoardDTO read(int bid) throws Exception {
		return bDao.read(bid);
	}

	@Override
	public boolean modify(BoardDTO bDto) throws Exception {
		return bDao.update(bDto) == 1;
	}

	@Override
	public boolean remove(int bid) throws Exception {
		return bDao.delete(bid) == 1;
	}

	@Override
	public List<BoardDTO> listAll(Criteria cri) throws Exception {
		return bDao.listAll(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return bDao.getTotalCnt(cri);
	}

	@Override
	public void increaseViewCnt(int bid) throws Exception {
		bDao.increaseViewCnt(bid);
		
	}

}
