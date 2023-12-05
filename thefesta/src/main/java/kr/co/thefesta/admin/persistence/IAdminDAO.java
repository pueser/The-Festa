package kr.co.thefesta.admin.persistence;

import java.util.List;

import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.member.domain.MemberDTO;

import java.util.List;
import java.util.Map;

import kr.co.thefesta.admin.domain.Criteria;
import kr.co.thefesta.admin.domain.QuestionDTO;
import kr.co.thefesta.admin.domain.ReportDTO;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IAdminDAO {
	//member 테이블 회원정보 list
	public List<MemberDTO> memberList(Criteria cri)throws Exception;
	//member 회원 디테일 정보
	public List<ReportDTO> memberDetail(String id, Criteria cri)throws Exception;
	//회원 신고내용
	public String memberReport(Integer reportid)throws Exception;
	//회원 신고글 삭제
	public int memberReportDelete(Integer reportid)throws Exception;
	//회원 신고당한 횟수
	public int memberReportnumRead(String id)throws Exception;
	//회원 신고내역 갯수
	public int memberReportCnt(String id)throws Exception;
	//신고 list
	public List<ReportDTO> reportList(Criteria cri)throws Exception;
	//신고 list 총 갯수
	public int reportListCnt() throws Exception;
	//신고처리상태 변경(reportstate->2)
	public int reportstateChange(Integer reportid)throws Exception;
	//회원신고 누적
	public int memberReportnumCnt(String id, Integer reportid)throws Exception;
	//member list 총 갯수
	public int memberListCnt() throws Exception;
	//축제list & 축제 문의list
	public List<Object> festaList(Criteria cri) throws Exception;
	//건의 list
	public List<QuestionDTO> questionList(Criteria cri, Integer contentid)throws Exception;
	//건의 총 갯수
	public int questionListCnt(Integer contentid)throws Exception;
	//건의 등록
	public void questionRegister(QuestionDTO questionDto)throws Exception;
	//축제 삭제
	public int festaDelete(Integer contentid)throws Exception;
	//축제 count
	public int festaListCnt()throws Exception;
	//축제 댓글 신고 등록
	public void festaReplyReport(ReportDTO reportDto)throws Exception;
	//게시글 댓글 신고 등록
	public int boardReplyReport(ReportDTO reportDto)throws Exception;
	//게시글 신고 등록
	public int boardReport(ReportDTO reportDto)throws Exception;
}
