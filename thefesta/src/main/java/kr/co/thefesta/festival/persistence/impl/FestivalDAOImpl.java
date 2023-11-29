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
	public List<FestivalDTO> readFestival(String startdate, String enddate) throws Exception {
		Map<String, String> date = new HashMap<>();
		date.put("startdate", startdate);
		date.put("enddate", enddate);
		
		return session.selectList("FestivalMapper.readFestival", date);
	}

	@Override
	public List<FestivalDTO> listAll(Criteria cri) throws Exception {
		return session.selectList("FestivalMapper.getListWithPaging", cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return session.selectOne("FestivalMapper.getTotalCnt", cri);
	}
}
