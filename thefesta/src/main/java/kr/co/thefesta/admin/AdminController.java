package kr.co.thefesta.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.admin.domain.MemberDTO;
import kr.co.thefesta.admin.service.IAdminService;
import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@Log4j
public class AdminController {
	@Autowired
	public IAdminService service;
	
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public List<MemberDTO> memberGet()throws Exception{
		log.info("member Get....");
		List<MemberDTO> memberList = service.memberList();
		log.info("memberList = " + memberList);
		
		return memberList;
	}
	
	@RequestMapping(value = "/memberList", method = RequestMethod.POST)
	public void memberPost()throws Exception{
		log.info("member Post....");
		
	}
	
	@RequestMapping(value = "/memberDetail", method = RequestMethod.GET)
	public void memberDetailGet()throws Exception{
		log.info("memberDetail Get...");
		
	}
}
