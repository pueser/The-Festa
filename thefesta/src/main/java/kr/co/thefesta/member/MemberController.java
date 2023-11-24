package kr.co.thefesta.member;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.thefesta.member.until.MailUtil;
import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.service.IMemberService;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
@Log4j
public class MemberController {

	@Autowired
	private IMemberService service;
	
	@PostMapping(value = "/loginPost") // o
	public MemberDTO loginPost(@RequestBody MemberDTO mDto) throws Exception {
		MemberDTO memInfo = service.login(mDto);
		log.info("MemberDTO ==> " + memInfo);
		
		return memInfo;
		
//		log.info("mDto......" + mDto);
//		
//		MemberDTO memInfo = service.login(mDto);
//		log.info("memInfo ===> " + memInfo);
		
//		if (memInfo == null) {
//			return "redirect:/member/login";
//		} else {
//			HttpSession session = request.getSession();
//			session.setAttribute("loginInfo", memInfo);
//			
//			log.info("session......" + session.getAttribute("loginInfo").toString());
//			model.addAttribute("memInfo", memInfo);
//			return "redirect:/member/login";
//		}
		
		
	}
	
	
//	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) throws Exception {
//		log.info("logout......");
//		Object obj = session.getAttribute("loginInfo");
//		
//		if (obj != null) {
//			
//			MemberDTO memInfo = (MemberDTO) obj;
//	        String id = memInfo.getId();
//	        
//			service.updateLogDate(id);
//			
//			session.removeAttribute("loginInfo");
//			session.invalidate();
//			
////			log.info("sessionRemove......" + session.getAttribute("loginInfo").toString()); 세션 삭제 확인용
//		}
//		
//		return "redirect:/";
//	}
//	
//	
//	
//	@RequestMapping(value = "/join", method = RequestMethod.GET)
//	public void join(){
//		log.info("join......");
//	}
//	
//	@RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
//    public void nicknameCheck(String nickname) throws Exception {
//        int nicknameCheck = service.nicknameCheck(nickname);
//        String stateCodeCheck = service.stateCodeCheck(nickname);
//        String nickCheckResult = "fail";
//        
//        Map<String, Object> result = new HashMap();
//        result.put("nickname", nicknameCheck);
//        result.put("stateCode", stateCodeCheck);
//        
////        log.info("nicknameCheck-----------");
////        log.info("nickname------------" + nickname);
////        log.info("stateCodeCheck" + stateCode);
//        
//        log.info(result);
//        
//        if(nicknameCheck != 0) {
//        	log.info("fail");
//		} else {
//			log.info("success");
//			nickCheckResult = "success";
//		}
//    }
//	
//	
//	@PostMapping(value = "/joinPost")
//	public String joinPost(MemberDTO mDto, Model model, RedirectAttributes rttr) throws Exception {
//		log.info("mDto......" + mDto.getId() + mDto.getPassword() + mDto.getNickname());
//		
////		String nicknameCheck = nicknameCheck(mDto.getNickname());
////		if ("result".equals(nicknameCheck) ) {
////			
////		}
//		
//		service.join(mDto);			
//		return "redirect:/member/login";
//	}
//	
//	
//	
//	@RequestMapping(value = "/pwcheck", method = RequestMethod.GET)
//	public void pwcheck(){
//		log.info("pwcheck......");
//	}
//	
//	@PostMapping(value = "/pwcheckPost")
//	public String pwcheckPost(MemberDTO mDto, HttpServletRequest request, Model model, RedirectAttributes rttr) throws Exception {
//		log.info("mDto......................." + mDto);
//		
//		MemberDTO memInfo = service.login(mDto);
//		log.info("memInfo ===> " + memInfo);
//		
//		if (memInfo == null) {
//			return "redirect:/member/pwcheck";
//		} else {
//			HttpSession session = request.getSession();
//			session.setAttribute("loginInfo", memInfo);
//			
//			log.info("session......" + session.getAttribute("loginInfo").toString());
//		}
//		model.addAttribute("memInfo", memInfo);
//		return "redirect:/pwchecknext";
//		
//	}
//	
//	@RequestMapping(value = "/pwcheckNext", method = RequestMethod.POST)
//	public String sendMail(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
//		String title = "메일 테스트 중입니다.";
//		String from = "dain7362@naver.com";
//		String text = "메일 테스트";
//		String to = "dain7362@gmail.com";
//		String cc = "";
//		
//		log.info(title);
//		
//		MailUtil.mailSend(title, from, text, to, cc);
//		return "redrect:/pwcheck";
//	}
	
	@PostMapping(value = "/updateState")
	public void updateState(@RequestBody MemberDTO mDto) throws Exception {
		String id = mDto.getId();
		String statecode = mDto.getStatecode();
		service.updateState(id, statecode);
	}
}