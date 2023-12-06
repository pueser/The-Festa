package kr.co.thefesta.member.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.persistence.IMemberDAO;
import lombok.extern.log4j.Log4j;

@Log4j
@Repository
public class MemberDAOImpl implements IMemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public MemberDTO selMember(String id) {
		return sqlSession.selectOne("MemberMapper.selMember", id);
	}

	@Override
	public MemberDTO login(MemberDTO mDto) {
		return sqlSession.selectOne("MemberMapper.login", mDto);
	}
	
	@Override
	public MemberDTO selLoginInfo(String id, String password) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("id", id);
		paramMap.put("password", password);
		return sqlSession.selectOne("MemberMapper.selLoginInfo", paramMap);
	}

	
	@Override
	public void updateFinalaccess(String id) {
		sqlSession.update("MemberMapper.updateFinalaccess", id);
	}

	@Override
	public int nicknameCheck(String nickname) {
		log.info(nickname);
		int result = sqlSession.selectOne("MemberMapper.nicknameCheck", nickname);
		log.info("닉네임 중복 ====> " + result);
		return sqlSession.selectOne("MemberMapper.nicknameCheck", nickname);
	}

	@Override
	public int idCheck(String id) {
		return sqlSession.selectOne("MemberMapper.idCheck", id);
	}

	@Override
	public void join(MemberDTO mDto) {
		log.info(mDto);
		sqlSession.selectOne("MemberMapper.join", mDto);
	}

	@Override
	public void reJoin(MemberDTO mDto) {
		sqlSession.selectOne("MemberMapper.reJoin", mDto);
	}

	
	@Override
	public void pwReset(Map<String, Object> paramMap) {
		sqlSession.update("MemberMapper.pwReset", paramMap);
	}
	
	
	@Override
	public void logout(String id) {
		sqlSession.update("MemberMapper.logout", id);
	}
	
	@Override
	public void updateState(MemberDTO mDto) {
		sqlSession.update("MemberMapper.updateState", mDto);
	}

	@Override
	public void updateImg(Map<String, Object> paramMap) {
		sqlSession.update("MemberMapper.updateImg", paramMap);
		
	}

	@Override
	public void memInfoReset(MemberDTO mDto) {
		sqlSession.update("MemberMapper.memInfoReset", mDto);
	}
}