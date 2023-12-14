package kr.co.thefesta.admin.persistence;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.thefesta.admin.domain.AdminDTO;
import kr.co.thefesta.admin.domain.Criteria;
import kr.co.thefesta.admin.domain.QuestionDTO;
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.member.domain.MemberDTO;

public interface IAdminDAO {
	//member 테이블 회원정보 list
	public List<AdminDTO> memberList(Criteria cri)throws Exception;
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
	//member 총 갯수
	public int memberCnt()throws Exception;
	//member list 총 갯수
	public int memberListCnt() throws Exception;
	//축제list & 축제 문의list
	public List<QuestionDTO> festaList(Criteria cri) throws Exception;
	//건의 list
	public List<QuestionDTO> questionList(Criteria cri, Integer contentid)throws Exception;
	//건의 총 갯수
	public int questionListCnt(Integer contentid)throws Exception;
	//건의 등록
	public void questionRegister(QuestionDTO questionDto)throws Exception;
	//건의 삭제
	public void questionDelete(String questionid)throws Exception;
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
	//게시글list(자유, 리뷰)
	public List<BoardDTO> boardlist(Criteria cri)throws Exception;
	//게시글 갯수(자유, 리뷰)
	public int boardListCnt()throws Exception;
	//문의사항list
	public List<BoardDTO> adminQuestionList(Criteria cri)throws Exception;
	//문의사항 갯수
	public int adminQuestionListCnt()throws Exception;
	//축제 자동삭제처리(1년기준)
	public void festivalSchdulerDelete(LocalDateTime time)throws Exception;
}
