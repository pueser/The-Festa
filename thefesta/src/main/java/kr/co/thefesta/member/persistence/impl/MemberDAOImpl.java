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
	
	private static final String namespace = "MemberMapper";
	
	@Override
	public MemberDTO selMember(MemberDTO mDto) {
		return sqlSession.selectOne(namespace + ".selMember", mDto);
	}

	@Override
	public MemberDTO login(MemberDTO mDto) {
		return sqlSession.selectOne(namespace + ".login", mDto);
	}
	
	@Override
	public MemberDTO selLoginInfo(String id, String password) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		paramMap.put("id", id);
		paramMap.put("password", password);
		return sqlSession.selectOne(namespace + ".selLoginInfo", paramMap);
	}

	
	@Override
	public void updateLogDate(String id) {
		sqlSession.update(namespace + ".loginUpdate", id);
	}

	@Override
	public String stateCodeCheck(String nickname) {
		return sqlSession.selectOne(namespace + ".stateCodeCheck", nickname);
	}

	@Override
	public int nicknameCheck(String nickname) {
		return sqlSession.selectOne(namespace + ".nicknameCheck", nickname);
	}

	@Override
	public int idCheck(String id) {
		return sqlSession.selectOne(namespace + ".idCheck", id);
	}

	@Override
	public void join(MemberDTO mDto) {
		sqlSession.selectOne(namespace + ".join", mDto);
	}

	@Override
	public void reJoin(MemberDTO mDto) {
		sqlSession.selectOne(namespace + ".reJoin", mDto);
	}

	
	@Override
	public void pwReset(Map<String, Object> paramMap) {
		sqlSession.update(namespace + ".pwReset", paramMap);
	}
	
	
	@Override
	public void logout(String id) {
		sqlSession.update(namespace + ".logout", id);
	}
	
	
	@Override
	public void updateState(MemberDTO mDto) {
		sqlSession.update(namespace + ".updateState", mDto);
	}

	@Override
	public void updateImg(String profileImg, String id) {
		Map<String, Object> paramMap = new HashMap<>();
		log.info("테스트" + profileImg + id);
		paramMap.put("profileImg", profileImg);
		paramMap.put("id", id);
		sqlSession.update(namespace + ".updateImg", paramMap);
		
	}
}