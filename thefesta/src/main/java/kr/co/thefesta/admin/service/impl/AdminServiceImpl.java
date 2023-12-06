package kr.co.thefesta.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.admin.domain.AdminDTO;
import kr.co.thefesta.admin.domain.Criteria;
import kr.co.thefesta.admin.domain.QuestionDTO;
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;
import kr.co.thefesta.admin.service.IAdminService;


@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	public IAdminDAO adminDao;
	
	@Override
	//member 테이블 회원정보 list
	public List<AdminDTO> memberList(Criteria cri) throws Exception {
		return adminDao.memberList(cri);
	}
	
	//member 회원 디테일 정보
	@Override
	public List<ReportDTO> memberDetail(String id, Criteria cri) throws Exception {
		return adminDao.memberDetail(id,cri);
	}
	
	//회원 신고내용
	@Override
	public String memberReport(Integer reportid) throws Exception {
		return adminDao.memberReport(reportid);
	}
	
	//회원 신고글 삭제
	@Override
	public int memberReportDelete(Integer reportid) throws Exception {
		return adminDao.memberReportDelete(reportid);
		
	}
	
	//회원 신고당한 횟수
	@Override
	public int memberReportnumRead(String id) throws Exception {
		return adminDao.memberReportnumRead(id);
	}
	
	//신고 list
	@Override
	public List<ReportDTO> reportList(Criteria cri) throws Exception {
		return adminDao.reportList(cri);
	}
	
	//신고 list 총 갯수
	@Override
	public int reportListCnt() throws Exception {
		return adminDao.reportListCnt();
	}
	
	//신고처리상태 변경(reportstate->2)
	@Override
	public int reportstateChange(Integer reportid) throws Exception {
		return adminDao.reportstateChange(reportid);
	}
	
	//회원 신고누적 count
	@Override
	public int memberReportnumCnt(String id, Integer reportid) throws Exception {
		return adminDao.memberReportnumCnt(id, reportid);
	}
	
	//member 총 갯수
	@Override
	public int memberCnt() throws Exception {
		return adminDao.memberCnt();
	}

	
	//member list 총 갯수
	@Override
	public int memberListCnt() throws Exception {
		return adminDao.memberListCnt();
	}
	
	//축제list
	@Override
	public List<QuestionDTO> festaList(Criteria cri) throws Exception {
		return adminDao.festaList(cri);
	}

	//건의 list
	@Override
	public List<QuestionDTO> questionList(Criteria cri, Integer contentid) throws Exception {
		return adminDao.questionList(cri, contentid);
	}
	
	//건의 총갯수
	@Override
	public int questionListCnt(Integer contentid) throws Exception {
		return adminDao.questionListCnt(contentid);
	}
	
	//축제 삭제
	@Override
	public int festaDelete(Integer contentid) throws Exception {
		return adminDao.festaDelete(contentid);
		
	}
	
	//건의 등록
	@Override
	public void questionRegister(QuestionDTO questionDto) throws Exception {
		 adminDao.questionRegister(questionDto);
	}
	
	//건의 삭제
	@Override
	public void questionDelete(String questionid) throws Exception {
		 adminDao.questionDelete(questionid);
		
	}
	
	//축제 댓글 신고 저장
	@Override
	public void festaReplyReport(ReportDTO reportDto) throws Exception {
		adminDao.festaReplyReport(reportDto);
		
	}
	
	//게시글 댓글 등록
	@Override
	public int boardReplyReport(ReportDTO reportDto) throws Exception {
		return adminDao.boardReplyReport(reportDto);
		
	}
	
	//게시글 신고 등록
	@Override
	public int boardReport(ReportDTO reportDto) throws Exception {
		return adminDao.boardReport(reportDto);
	}
	
	//회원 신고 내역 갯수
	@Override
	public int memberReportCnt(String id) throws Exception {
		return adminDao.memberReportCnt(id);
	}
	
	//축제 count
	@Override
	public int festaListCnt() throws Exception {
		return adminDao.festaListCnt();
	}

	
	


	
	

}