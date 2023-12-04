package kr.co.thefesta.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.admin.domain.Criteria;
import kr.co.thefesta.admin.domain.PageDTO;
import kr.co.thefesta.admin.domain.QuestionDTO;
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.service.IAdminService;
import kr.co.thefesta.festival.service.IFestivalService;
import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.service.IMemberService;
import lombok.extern.log4j.Log4j;
import oracle.ons.CreatePermission;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@Log4j
public class AdminController {
	@Autowired
	public IAdminService service;
	
	@Autowired
	private IFestivalService festivalService;
	
	@Autowired
	private IMemberService memberService;
	
	
	//member 테이블 회원정보 list
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public Map<String, Object> memberGet(Criteria cri)throws Exception{
		log.info("memberlist Get....");
		log.info("cri = " + cri);
		Map<String, Object> result = new HashMap<>();
		
		List<MemberDTO> memberList = service.memberList(cri);
		
		
		for (MemberDTO member : memberList) {
			if(member.getStatecode().equals("1")) {
				member.setStatecode("일반");
			}else if(member.getStatecode().equals("2")) {
				member.setStatecode("탈퇴");
			}else if(member.getStatecode().equals("3")) {
				member.setStatecode("재가입 가능");
			}else {
				member.setStatecode("강퇴");
			}
		}
		result.put("list", memberList);
		int total = service.memberListCnt();
		
		 
	     log.info("total : " + total);
	     
	     result.put("pageMaker", new PageDTO(cri, total));
		
		return result;
	}
	
	
	//회원 상세페이지(신고 list 페이지)
	@RequestMapping(value = "/memberDetail", method = RequestMethod.GET)
	public List<ReportDTO> memberDetailGet(String id)throws Exception{
		log.info("memberDetail Get...");
		log.info("회원 상세 id값 = " + id);
		List<ReportDTO> reportList = service.memberDetail(id);
		
		//ReportDTO rbno,rfrno,rbid를 reportnumber(화면표시글자로) 변경 
		for (ReportDTO reportDTO : reportList) {
			if(reportDTO.getRbrno() > 0) {
				reportDTO.setReportnumber("게시글 댓글 코드");
			}else if(reportDTO.getRfrno() > 0) {
				reportDTO.setReportnumber("축제 댓글 코드");
			}else if(reportDTO.getRbid() > 0){
				reportDTO.setReportnumber("게시글 코드");
			}else {
				reportDTO.setReportnumber("해당 글은 삭제되었습니다.(회원탈퇴)");// 회원(reported)이 탈퇴한 경우
			}
		}
		
		log.info("reportList = " + reportList.toString());
		return reportList;
	}
	
	//신고내용 표시
	@RequestMapping(value = "/memberReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String memberReportGet(Integer reportid)throws Exception {
		log.info("memberReport Get...");
		String reportcontent = service.memberReport(reportid);
		log.info("신고내용 = " + reportcontent);
		
		return reportcontent;
	}
	
	//신고내용 삭제
	@RequestMapping(value = "/memberReportDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String memberReportDelete(Integer reportid)throws Exception {
		log.info("memberReportDelete post...");
		log.info("react에서 받은 값 : " + reportid);
		
		int num = service.memberReportDelete(reportid);
		String result;
		
		//결과 
		if(num == 1) {
			log.info(reportid + "번 신고글이 삭제 되었습니다.");
			result = (reportid + "번 신고글이 삭제 되었습니다.");
			
		}else {
			log.info(reportid + "번 신고글 삭제 실패하였습니다.");
			result = (reportid + "번 신고글 삭제 실패하였습니다.");
		}
		
		return result;
	}
	
	//신고 list
	@RequestMapping(value = "/reportList", method = RequestMethod.GET)
	public Map<String, Object> reportListGet(Criteria cri)throws Exception{
		
		log.info("reportList Get...");
		List<ReportDTO> reportList = service.reportList(cri);
		log.info("reportList = " + reportList.toString());
		log.info("cri = " + cri);
		Map<String, Object> result = new HashMap<>();
		
		//ReportDTO rbno,rfrno,rbid를 reportnumber(화면표시글자로) 변경 
		for (ReportDTO reportDTO : reportList) {
			if(reportDTO.getRbrno() > 0) {
				reportDTO.setReportnumber("게시글 댓글 코드");
			}else if(reportDTO.getRfrno() > 0) {
				reportDTO.setReportnumber("축제 댓글 코드");
			}else if(reportDTO.getRbid() > 0){
				reportDTO.setReportnumber("게시글 코드");
			}else {
				reportDTO.setReportnumber("해당 글은 삭제되었습니다.(회원탈퇴)");// 회원(reported)이 탈퇴한 경우
			}
		}
		
		result.put("list", reportList);
		int total = service.reportListCnt();
		 
	    log.info("total : " + total);
	     
	    result.put("pageMaker", new PageDTO(cri, total));
		
		return result;
	}
	
	//신고처리상태 변경(reportstate->2)
	@RequestMapping(value = "/reportstateChange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String reportstateChange(Integer reportid, String id)throws Exception{
		log.info("reportstateChange get...");
		log.info("리액트에서 받은 reportid값 = " + reportid);
		log.info("리액트에서 받은 id값 = " + id);
		
		String result;
		

		int num = service.reportstateChange(reportid, id);
		
		//결과 
		if(num == 1) {
			log.info(reportid + "번 신고글이 승인 되었습니다.");
			result = reportid + "번 신고글이 승인 되었습니다.";
		}else {
			log.info(reportid + "번 신고글 승인이 실패하였습니다.");
			result = reportid + "번 신고글 승인이 실패하였습니다.";
		}
		
		
		return result;
	}
	
	//신고 누적횟수 
	@RequestMapping(value = "/memberReportnumRead", method = RequestMethod.GET)
	public int memberReportnumRead(Integer reportid, String id)throws Exception{
		log.info("memberReportnumRead get...");
		log.info("리액트에서 받은 reportid값 = " + reportid);
		log.info("리액트에서 받은 id값 = " + id);
		int reportnum = service.memberReportnumRead(id);
		
		return reportnum;
	}
	
	
	//축제list & 축제 문의list
	@RequestMapping(value = "/festaList", method = RequestMethod.GET)
	public Map<String, Object> festaList(kr.co.thefesta.festival.domain.Criteria cri)throws Exception{
		log.info("festaList Get....");
		log.info("cri = "+ cri.toString());
		List<Object> festaList = service.festaList(cri);
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("questionDto", festaList);
		int total = festivalService.getTotalCnt(cri);
		
		 
	    log.info("total : " + total);
	     
	    result.put("pageMaker", new kr.co.thefesta.festival.domain.PageDTO(cri, total));
		return result;
	}
	
	//축제 건의내용 list
	@RequestMapping(value = "/questionList", method = RequestMethod.GET)
	public Map<String, Object> questionList(Criteria cri, Integer contentid)throws Exception{
		log.info("questionList get...");
		log.info("받은 값 = " + contentid);
		log.info("받은 값 = " + cri.toString());
		
		List<QuestionDTO> list = service.questionList(cri, contentid);
		log.info("list = " + list.toString());
		
		Map<String, Object> result = new HashMap<>();
		
		result.put("list", list);
		int total = service.questionListCnt(contentid);
		
		 log.info("total : " + total);
	     
		 result.put("pageMaker", new PageDTO(cri, total));
		
		return result;
	}
	
	//축제 삭제
	@RequestMapping(value = "/festaDelete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String festaDelete(Integer contentid) throws Exception{
		log.info("festaDelete post...");
		log.info("받은 값" + contentid);
		
		int delete = service.festaDelete(contentid);
		
		String result;
		if(delete == 1) {
			result = contentid + "번이 삭제되었습니다.";
		}else {
			result = contentid + "번이 삭제 실패하였습니다.";
		}
		
		log.info("result = " + result);
		
		return result;
	}
	
	//축제 건의내용 저장
	@RequestMapping(value = "questionRegister", method = RequestMethod.POST)
	public void questionCreate(QuestionDTO questionDto) throws Exception{
		log.info("questionCreate post...");
		
		log.info("받은 값 = " + questionDto.toString());
		
		service.questionRegister(questionDto);
	}
	
	//축제 댓글 신고(parameter : reportcontent, reporter, reported, rfrno)
	@RequestMapping(value = "/festaReplyReport", method = RequestMethod.POST)
	public void festaReplyReport(ReportDTO reportDto) throws Exception{
		log.info("festaReplyReport post...");
		log.info("받은 값 = " + reportDto.toString());
		
		service.festaReplyReport(reportDto);
	}
	
	//게시글 댓글 신고(parameter : reportcontent, reporter, reported, rbrno)
	@RequestMapping(value = "/boardReplyReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String boardReplyReport(ReportDTO reportDto) throws Exception{
		log.info("boardReplyReport post ...");
		String num = memberService.stateCodeCheck(reportDto.getReported());
		log.info("받은 값 = " + reportDto.toString());
		log.info("회원 상태코드 = " + num);
		
		if(num.equals("1")) {
			int result = service.boardReplyReport(reportDto);
			return result + "댓글이 신고 되었습니다.";
		}else {
			return "회원은 현재 사이트의 회원이 아닙니다. 신고가 불가능 합니다. 관리자에게 문의 바람니다.";
		}
	}
	
	//게시글 신고 (parameter : reportcontent, reporter, reported, rbid)
	@RequestMapping(value = "boardReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String boardReport(ReportDTO reportDto) throws Exception{
		log.info("boardReport post ...");
		String num = memberService.stateCodeCheck(reportDto.getReported());
		log.info("받은 값 = " + reportDto.toString());
		log.info("회원 상태코드 = " + num);
		
		if(num.equals("1")) {
			int result = service.boardReport(reportDto);
			return result + "게시글이 신고 되었습니다.";
		}else {
			return "회원은 현재 사이트의 회원이 아닙니다. 신고가 불가능 합니다. 관리자에게 문의 바람니다.";
		}
	}
	 
	//회원 상태코드 변경
//	@RequestMapping(value = "/memberState", method = RequestMethod.POST)
//	public String memberStatePost(String statecode, String id)throws Exception {
//		log.info("statecode = " + statecode);
//		log.info("id = " + id);
//		return null;
//	}
}