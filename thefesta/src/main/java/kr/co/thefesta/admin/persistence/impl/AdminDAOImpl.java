package kr.co.thefesta.admin.persistence.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.thefesta.admin.domain.AdminDTO;
import kr.co.thefesta.admin.domain.Criteria;

import kr.co.thefesta.admin.domain.QuestionDTO;
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;
import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.persistence.IMemberDAO;
import lombok.extern.log4j.Log4j;


@Repository
@Log4j
public class AdminDAOImpl implements IAdminDAO {
	//admin mapper
	@Autowired
	private SqlSession session;
	
	//member IMemberDAO
	@Autowired
	private IMemberDAO memberDao;
	
	
	//member 테이블 회원정보 list
	@Override
	public List<AdminDTO> memberList(Criteria cri) throws Exception {
		return session.selectList("AdminMapper.memberList", cri);
	}
	
	//member 회원 디테일 정보
	@Override
	public List<ReportDTO> memberDetail(String id, Criteria cri) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("pageNum", cri.getPageNum());
		map.put("amount", cri.getAmount());
		
		return session.selectList("AdminMapper.memberDetail", map);
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
	@Override
	public int reportstateChange(Integer reportid) throws Exception {
		return session.update("AdminMapper.reportstateChange", reportid);
	}
	
	//회원 신고 누적
	@Transactional
	@Override
	public int memberReportnumCnt(String id, Integer reportid) throws Exception {
		int num = session.selectOne("AdminMapper.memberReportnumRead", id);// 회원 reportnum 횟수
		log.info("기존의 누적 갯수 = "+ num);
		
		Map<String, Object> map = new HashMap<>();
		map.put("num" , num);
		map.put("id", id);
		
		MemberDTO mDto = new MemberDTO(); 
		mDto.setId(id);
		mDto.setStatecode("4");

		
		//num갯수가 4번이면 reportnum 1추가 , 회원state코드 4번(강퇴), 신고글 statecode 4 변경
		if((num+1) == 5) {
			memberDao.updateState(mDto);//멤버의 상태코드 변경
			session.delete("AdminMapper.memberReportstateChange4", id); //신고글 모두 상태코드4변경
			return session.update("AdminMapper.memberReportnumUpdate", map);// 회원 reportnum + 1
			
		}else {
			session.update("AdminMapper.memberReportstateChange3", reportid);//회원 신고글 3번변경
			return session.update("AdminMapper.memberReportnumUpdate", map);// 회원 reportnum + 1
		}
		
	}
	//member 총 갯수
	@Override
	public int memberCnt() throws Exception {
		return session.selectOne("AdminMapper.memberCnt");
	}

	
	//member list 총 갯수
	@Override
	public int memberListCnt() throws Exception {
		
		return session.selectOne("AdminMapper.memberListCnt");
	}
	
	//축제list
	@Override
	public List<QuestionDTO> festaList(Criteria cri) throws Exception {
		
		List<QuestionDTO> list = session.selectList("AdminMapper.festaList", cri); 
		
		log.info("list = " + list.toString());
		
		
		
		return list;
	}
	
	//축제 count
	@Override
	public int festaListCnt() throws Exception {
		return session.selectOne("AdminMapper.festaListCnt");
	}

	
	//회원 신고당한 횟수
	@Override
	public int memberReportnumRead(String id) throws Exception {
		
		return session.selectOne("AdminMapper.memberReportnumRead", id);
	}
	
	//건의 list
	@Override
	public List<QuestionDTO> questionList(Criteria cri, Integer contentid) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", cri.getPageNum());
		map.put("amount", cri.getAmount());
		map.put("contentid", contentid);
		
		return session.selectList("AdminMapper.questionList", map);
	}

	//건의 총 갯수
	@Override
	public int questionListCnt(Integer contentid) throws Exception {
		return session.selectOne("AdminMapper.questionListCnt", contentid);
	}
	
	//건의 삭제
	@Override
	public void questionDelete(String questionid) throws Exception {
		session.delete("AdminMapper.questionDelete", questionid);
		
	}

	
	
	//축제 삭제
	@Transactional
	@Override
	public int festaDelete(Integer contentid) throws Exception {
//		session.delete("AdminMapper.festaImageDelete", contentid); //축제 이미지 삭제
//		session.delete("AdminMapper.festaLikeDelete", contentid);// 축제 좋아요 삭제
//		session.delete("AdminMapper.festaQuestionDelete", contentid);// 축제 건의 삭제
//		List<ReportDTO> list = session.selectList("AdminMapper.deleteReportidSelect", contentid);// 삭제할 축제댓글
//		log.info("reportidList " + list.toString());
//		
//		session.delete("AdminMapper.reportFrnoDelete", list);// 축제댓글 신고 삭제
//		session.delete("AdminMapper.festaReplyDelete", contentid);// 축제 댓글 삭제
		
		return session.delete("AdminMapper.festaDelete", contentid);// 축제 삭제
		
	}
	
	//축제 건의 등록
	@Override
	public void questionRegister(QuestionDTO questionDto) throws Exception {
		session.insert("AdminMapper.questionRegister", questionDto);
	}
	
	//축제 댓글 신고 등록
	@Override
	public void festaReplyReport(ReportDTO reportDto) throws Exception {
		session.insert("AdminMapper.festaReplyReport",reportDto);
		
	}
	
	//게시글 댓글 신고 등록
	@Override
	public int boardReplyReport(ReportDTO reportDto) throws Exception {
		return session.insert("AdminMapper.boardReplyReport", reportDto);
		
	}
	
	//게시글 신고 등록
	@Override
	public int boardReport(ReportDTO reportDto) throws Exception {
		return session.insert("AdminMapper.boardReport", reportDto);
	}
	
	//회원 신고 내역 갯수
	@Override
	public int memberReportCnt(String id) throws Exception {
		return session.selectOne("AdminMapper.memberReportCnt", id);
	}
	
	//게시글list(자유, 리뷰)
	@Override
	public List<BoardDTO> boardlist(Criteria cri) throws Exception {
		return session.selectList("AdminMapper.boardlist", cri);
	}
	//게시글 갯수(자유, 리뷰)
	@Override
	public int boardListCnt() throws Exception {
		return session.selectOne("AdminMapper.boardListCnt");
	}
	//문의사항list
	@Override
	public List<BoardDTO> adminQuestionList(Criteria cri) throws Exception {
		return session.selectList("AdminMapper.adminQuestionList", cri);
	}
	//문의사항 갯수
	@Override
	public int adminQuestionListCnt() throws Exception {
		return session.selectOne("AdminMapper.adminQuestionListCnt");
	}
	
	
	//축제 자동삭제처리(1년기준)
	@Override
	public void festivalSchdulerDelete(String time) throws Exception {
		log.info("전달 받은 값 = " + time);
		
		session.delete("AdminMapper.festivalSchdulerDelete", time);//축제 삭제(1년이 지난경우)
		session.selectList("AdminMapper.boardSchuderList");//게시글 (신고누적이 없는) list
		
		
		
	}

	

	
	
	
	

}