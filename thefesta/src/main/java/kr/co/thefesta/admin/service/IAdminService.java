package kr.co.thefesta.admin.service;

import java.util.List;

import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.member.domain.MemberDTO;

public interface IAdminService {
	
	//member 테이블 회원정보 list
	public List<MemberDTO> memberList()throws Exception;
	//member 회원 디테일 정보
	public List<ReportDTO> memberDetail(String id)throws Exception;
	//회원 신고내용
	public String memberReport(Integer reportid)throws Exception;
	//회원 신고글 삭제
	public int memberReportDelete(Integer reportid)throws Exception;
	//신고 list
	public List<ReportDTO> reportList()throws Exception;
	//신고처리상태 변경(reportstate->2)
	public int reportstateChange(Integer reportid)throws Exception;
}
