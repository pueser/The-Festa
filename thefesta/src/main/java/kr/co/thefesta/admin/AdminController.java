package kr.co.thefesta.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public List<MemberDTO> memberGet()throws Exception{
		log.info("memberlist Get....");
		List<MemberDTO> memberList= service.memberList();
		
		log.info("memberList = " + memberList);
		
		return memberList;
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
	@RequestMapping(value = "/memberReport", method = RequestMethod.GET)
	public void memberReportGet()throws Exception {
		log.info("memberReport Get...");
	}
	
	//회원 상태코드 변경
	@RequestMapping(value = "/memberState", method = RequestMethod.POST)
	public String memberStatePost(String statecode, String id)throws Exception {
		log.info("statecode = " + statecode);
		log.info("id = " + id);
		return null;
	}
}
