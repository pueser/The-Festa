package kr.co.thefesta.admin.persistence.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.thefesta.admin.AdminController;
import kr.co.thefesta.admin.domain.Criteria;
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;

import kr.co.thefesta.member.domain.MemberDTO;
import lombok.extern.log4j.Log4j;


@Repository
@Log4j
public class AdminDAOImpl implements IAdminDAO {
	@Autowired
	private SqlSession session;
	//member 테이블 회원정보 list
	@Override
	public List<MemberDTO> memberList(Criteria cri) throws Exception {
		
		return session.selectList("AdminMapper.memberList", cri);
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
	public List<ReportDTO> reportList(Criteria cri) throws Exception {
		return session.selectList("AdminMapper.reportList", cri);
	}
	
	//신고 list 총 갯수
	@Override
	public int reportListCnt() throws Exception {
		return session.selectOne("AdminMapper.reportListCnt");
	}
	
	//신고처리상태 변경(reportstate->2)
	@Transactional
	@Override
	public int reportstateChange(Integer reportid, String id) throws Exception {
		int num = session.selectOne("AdminMapper.memberReportnumRead", id);
		log.info("기존의 누적 갯수 = "+ num);
		
		Map<String, Object> map = new HashMap<>();
		map.put("num" , num);
		map.put("id", id);
		
		//num갯수가 4번이면, state코드 4번 변경(강퇴)
		if(num == 4) {
			session.update("AdminMapper.memberReportnumUpdate", map);
			return session.update("AdminMapper.reportstateChange", reportid);
		}else {
			session.update("AdminMapper.memberReportnumUpdate", map);
			return session.update("AdminMapper.reportstateChange", reportid);
		}
		
	}
	
	//member list 총 갯수
	@Override
	public int memberListCnt() throws Exception {
		
		return session.selectOne("AdminMapper.memberListCnt");
	}
	
	//축제list & 축제 문의list
	@Override
	public Map<String, Object> festaList(Criteria cri) throws Exception {
		//List<String> list = new ArrayList<>();
		List<String> list = session.selectList("AdminMapper.festaList", cri);
		log.info("lsit" + list.toString());
		return null;
	}

	
	
}