package kr.co.thefesta.member.persistence.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.persistence.IMemberDAO;

@Repository
public class MemberDAOImpl implements IMemberDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "kr.co.myletter.memberMapper";
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberDTO mDto) {
		sqlSession.insert(namespace + ".insertMember", mDto);
	}

	@Override
	public MemberDTO selMember(String id) throws Exception {
		return sqlSession.selectOne(namespace + ".selMember", id);
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

}