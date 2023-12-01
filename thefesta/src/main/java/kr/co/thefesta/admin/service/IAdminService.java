package kr.co.thefesta.admin.service;

import java.util.List;
import java.util.Map;

import kr.co.thefesta.admin.domain.Criteria;
import kr.co.thefesta.admin.domain.ReportDTO;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IAdminService {
	
	//member 테이블 회원정보 list
	public List<MemberDTO> memberList(Criteria cri)throws Exception;
	//member 회원 디테일 정보
	public List<ReportDTO> memberDetail(String id)throws Exception;
	//회원 신고내용
	public String memberReport(Integer reportid)throws Exception;
	//회원 신고글 삭제
	public int memberReportDelete(Integer reportid)throws Exception;
	//신고 list
	public List<ReportDTO> reportList(Criteria cri)throws Exception;
	//신고 list 총 갯수
	public int reportListCnt() throws Exception;
	//신고처리상태 변경(reportstate->2)
	public int reportstateChange(Integer reportid, String id)throws Exception;
	//member list 총 갯수
	public int memberListCnt() throws Exception;
	//축제list & 축제 문의list
	public Map<String, Object> festaList(Criteria cri) throws Exception;
	
}
