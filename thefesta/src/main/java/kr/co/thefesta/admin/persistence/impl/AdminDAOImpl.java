package kr.co.thefesta.admin.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.admin.domain.MemberDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;


@Repository
public class AdminDAOImpl implements IAdminDAO {
	@Autowired
	private SqlSession session;
	
	//member 테이블 회원정보 list
	@Override
	public List<MemberDTO> memberList() throws Exception {
		return session.selectList("AdminMapper.memberList");
	}
	
	//member 회원 디테일 정보
	@Override
	public MemberDTO memberDetail(String nickname) throws Exception {
		return session.select("AdminMapper.memberDetail", nickname);
	}
}
