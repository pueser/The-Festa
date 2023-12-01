package kr.co.thefesta.scheduler.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.scheduler.domain.SchedulerCri;
import kr.co.thefesta.scheduler.persistence.ISchedulerDAO;

@Repository
public class SchedulerDAOImpl implements ISchedulerDAO {
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int getFestaCnt(SchedulerCri sCri) throws Exception {
		return session.selectOne("SchedulerMapper.getFestaCnt", sCri);
	}

	@Override
	public List<FestivalDTO> getFestaList(SchedulerCri sCri) throws Exception {
		return session.selectList("SchedulerMapper.getFestaList", sCri);
	}

	@Override
	public List<AreaCodeDTO> getDistrictSelectOptions(AreaCodeDTO acDTO) throws Exception {
		return session.selectList("SchedulerMapper.getDistrictSelectOptions", acDTO);
	}
}
