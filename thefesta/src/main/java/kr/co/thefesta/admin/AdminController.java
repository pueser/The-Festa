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
import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.service.IAdminService;


import kr.co.thefesta.member.domain.MemberDTO;
import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@Log4j
public class AdminController {
	@Autowired
	public IAdminService service;
	
	
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
		int num = service.reportstateChange(reportid, id);
		
		String result;
		
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
	
	//member 테이블 회원정보 list
//		@RequestMapping(value = "/memberList", method = RequestMethod.GET)
//		public Map<String, Object> memberGet(Criteria cri)throws Exception{
//			log.info("memberlist Get....");
//			log.info("cri = " + cri);
//			Map<String, Object> result = new HashMap<>();
//			
//			List<MemberDTO> memberList = service.memberList(cri);
//			
//			
//			for (MemberDTO member : memberList) {
//				if(member.getStatecode().equals("1")) {
//					member.setStatecode("일반");
//				}else if(member.getStatecode().equals("2")) {
//					member.setStatecode("탈퇴");
//				}else if(member.getStatecode().equals("3")) {
//					member.setStatecode("재가입 가능");
//				}else {
//					member.setStatecode("강퇴");
//				}
//			}
//			result.put("list", memberList);
//			int total = service.memberListCnt();
//			
//			 
//		     log.info("total : " + total);
//		     
//		     result.put("pageMaker", new PageDTO(cri, total));
//			
//			return result;
//		}
	
	//축제list & 축제 문의list
	@RequestMapping(value = "/festaList", method = RequestMethod.GET)
	public Map<String, Object> festaList(Criteria cri)throws Exception{
		log.info("festaList Get....");
		log.info("cri = "+ cri.toString());
		service.festaList(cri);
		return null;
	}
	
	
	//회원 상태코드 변경
	@RequestMapping(value = "/memberState", method = RequestMethod.POST)
	public String memberStatePost(String statecode, String id)throws Exception {
		log.info("statecode = " + statecode);
		log.info("id = " + id);
		return null;
	}
}