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
		log.info("member Get....");
		List<MemberDTO> memberList= service.memberList();
		
		//member statecode(회원상태)번호를 상태글자로 변경
		for (MemberDTO memberStatecode : memberList) {
			if(memberStatecode.getStatecode().equals("1")) {
				memberStatecode.setStatecode("일반");
			}else if(memberStatecode.getStatecode().equals("2")) {
				memberStatecode.setStatecode("탈퇴");
			}else if(memberStatecode.getStatecode().equals("3")) {
				memberStatecode.setStatecode("재가입 가능");
			}else {
				memberStatecode.setStatecode("강퇴");
			}
		}
		
		log.info("memberList = " + memberList);
		
		return memberList;
	}
	
	@RequestMapping(value = "/memberList", method = RequestMethod.POST)
	public void memberPost()throws Exception{
		log.info("member Post....");
		
	}
	
	//회원 상세페이지(신고 list 페이지)
	@RequestMapping(value = "/memberDetail", method = RequestMethod.GET)
	public List<ReportDTO> memberDetailGet(String id)throws Exception{
		log.info("memberDetail Get...");
		log.info("회원 상세 id값 = " + id);
		List<ReportDTO> reportList = service.memberDetail(id);
		log.info("reportList = " + reportList.toString());
		
		return reportList;
	}
}
