package kr.co.thefesta.festival.persistence.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.LikeDTO;
import kr.co.thefesta.festival.persistence.IFestivalDAO;

@Repository
public class FestivalDAOImpl implements IFestivalDAO {
	@Autowired
	private SqlSession session;

	@Override
	public void insertApi(FestivalDTO fDto) throws Exception {
		session.insert("FestivalMapper.insertApi", fDto);
	}

	@Override
	public void insertImg(FestivalImageDTO fiDto) throws Exception {
		session.insert("FestivalMapper.insertImg", fiDto);
	}

	@Override
	public void insertAreaCode(AreaCodeDTO aDto) throws Exception {
		session.insert("FestivalMapper.insertAreaCode", aDto);	
	}

	@Override
	public List<FestivalDTO> listAll(Criteria cri) throws Exception {
		return session.selectList("FestivalMapper.getListWithPaging", cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return session.selectOne("FestivalMapper.getTotalCnt", cri);
	}

	@Override
	public int searchImg(String serialnum) throws Exception {
		return session.selectOne("FestivalMapper.searchImg", serialnum);
	}

	@Override
	public int updateFestival(FestivalDTO fDto) throws Exception {
		return session.update("FestivalMapper.updateFestival", fDto);
	}

	@Override
	public List<AreaCodeDTO> getAreaCode() throws Exception {
		return session.selectList("FestivalMapper.getAreaCode");
	}

	@Override
	public List<FestivalImageDTO> getImg(String contentid) throws Exception {
		return session.selectList("FestivalMapper.getImg", contentid);
	}

	@Override
	public int insertLike(LikeDTO lDto) throws Exception {
		return session.insert("FestivalMapper.insertLike", lDto);
	}

	@Override
	public int deleteLike(LikeDTO lDto) throws Exception {
		return session.delete("FestivalMapper.deleteLike", lDto);
	}

	@Override
	public List<LikeDTO> searchLike(LikeDTO lDto) throws Exception {
		return session.selectList("FestivalMapper.searchLike", lDto);
	}
}
