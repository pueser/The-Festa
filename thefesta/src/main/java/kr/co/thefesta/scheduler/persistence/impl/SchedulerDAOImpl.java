//package kr.co.thefesta.scheduler.persistence.impl;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import kr.co.thefesta.scheduler.persistence.ISchedulerDAO;
//
//@Repository
//public class SchedulerDAOImpl implements ISchedulerDAO {
//	
//	@Autowired
//	private SqlSession session;
//	
//	@Override
//	public int getFestaCnt(int date) throws Exception {
//		return session.selectOne("SchedulerMapper.getFestaCnt", date);
//	}
//}
