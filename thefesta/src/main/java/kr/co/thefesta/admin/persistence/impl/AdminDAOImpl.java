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
import kr.co.thefesta.admin.domain.QuestionDTO;
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;
import kr.co.thefesta.festival.mapper.FestivalMapper;
import kr.co.thefesta.member.domain.MemberDTO;
import lombok.extern.log4j.Log4j;


@Repository
@Log4j
public class AdminDAOImpl implements IAdminDAO {
	//admin mapper
	@Autowired
	private SqlSession session;
	//member mapper
	@Autowired
	private kr.co.thefesta.member.mapper.MemberMapper memberSession;
	//festival mapper 이거 안됨ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ
	@Autowired
	private FestivalMapper festivalSession;
	
	
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
		int num = session.selectOne("AdminMapper.memberReportnumRead", id);// 회원 reportnum 횟수
		log.info("기존의 누적 갯수 = "+ num);
		
		Map<String, Object> map = new HashMap<>();
		map.put("num" , num);
		map.put("id", id);
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("statecode", 4);
		
		//num갯수가 4번이면 reportnum 추가 후 , state코드 4번(강퇴) 변경
		if((num+1) == 5) {
			session.update("AdminMapper.memberReportnumUpdate", map);// 회원 reportnum + 1
			
			memberSession.updateState(paramMap); //회원 상태코드 4번 변경
			return session.update("AdminMapper.reportstateChange", reportid); // 신고처리상태 변경(reportstate->2)
			
		}else {
			session.update("AdminMapper.memberReportnumUpdate", map);// 회원 reportnum + 1
			return session.update("AdminMapper.reportstateChange", reportid);// 신고처리상태 변경(reportstate->2)
		}
		
	}
	
	//member list 총 갯수
	@Override
	public int memberListCnt() throws Exception {
		
		return session.selectOne("AdminMapper.memberListCnt");
	}
	
	//축제list
	@Override
	public List<Object> festaList(kr.co.thefesta.festival.domain.Criteria cri) throws Exception {
		List<Object> list = new ArrayList<>();
		
		List<QuestionDTO> list1 = session.selectList("AdminMapper.festaList", cri); //건의 갯수 있는 축제list
		List<QuestionDTO> list2 = session.selectList("AdminMapper.festaListAll", cri);//건의 갯수 없는 축제list
		
		for (QuestionDTO festaList : list2) {
			list.add(festaList);
		}
		
		for (QuestionDTO festaList : list1) {
			list.add(festaList);
		}
		log.info("list = " + list);
		
		
		
		return list;
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
	
	
	
	//축제 삭제
	@Transactional
	@Override
	public int festaDelete(Integer contentid) throws Exception {
		session.delete("AdminMapper.festaImageDelete", contentid); //축제 이미지 삭제
		session.delete("AdminMapper.festaLikeDelete", contentid);// 축제 좋아요 삭제
		session.delete("AdminMapper.questionDelete", contentid);// 축제 건의 삭제
		List<ReportDTO> list = session.selectList("AdminMapper.deleteReportidSelect", contentid);// 삭제할 축제댓글
		log.info("reportidList " + list.toString());
		
		session.delete("AdminMapper.reportFrnoDelete", list);// 축제댓글 신고 삭제
		session.delete("AdminMapper.festaReplyDelete", contentid);// 축제 댓글 삭제
		
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

	
	
}