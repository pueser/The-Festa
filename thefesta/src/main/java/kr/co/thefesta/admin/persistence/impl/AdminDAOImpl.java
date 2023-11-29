package kr.co.thefesta.admin.persistence.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;
import kr.co.thefesta.member.domain.MemberDTO;


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
	public List<ReportDTO> memberDetail(String id) throws Exception {
		return session.selectList("AdminMapper.memberDetail", id);
	}
	
	//회원 신고내용
	@Override
	public String memberReport(Integer reportid) throws Exception {
		return session.selectOne("AdminMapper.memberReport", reportid);
	}
	
	//회원 신고글 삭제
	@Override
	public int memberReportDelete(Integer reportid) throws Exception {
		return session.delete("AdminMapper.memberReportDelete", reportid);
		
	}
	
	//신고 list
	@Override
	public List<ReportDTO> reportList() throws Exception {
		return session.selectList("AdminMapper.reportList");
	}
		
	//신고처리상태 변경(reportstate->2)
	@Override
	public int reportstateChange(Integer reportid) throws Exception {
		return session.update("AdminMapper.reportstateChange", reportid);
	}
}
