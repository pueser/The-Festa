package kr.co.thefesta.festival.persistence.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.persistence.IFestivalDAO;

@Repository
public class FestivalDAOImpl implements IFestivalDAO {
	@Autowired
	private SqlSession session;

	@Override
	public void insert(FestivalDTO fDto) throws Exception {
		session.insert("FestivalMapper.insertApi", fDto);
	}
}
