package kr.co.thefesta.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController; // RestController
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.thefesta.member.until.MailUtil;
import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.service.IMemberService;
import lombok.extern.log4j.Log4j;

@Controller // jsp 파일로 실험해볼 땐 RestController 쓰면 안 됨...
@RequestMapping("/member")
@CrossOrigin(origins = "*")
@Log4j // 임의로 추가
public class MemberController {

	@Autowired
	private IMemberService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("mDto") MemberDTO mDto) {
		log.info("member loginGet .............");
	}
	
	@PostMapping(value = "/loginPost")
	public String loginPost(MemberDTO mDto, HttpServletRequest request, Model model, RedirectAttributes rttr) {
		log.info("mDto......................." + mDto);
		
		MemberDTO memInfo = service.login(mDto);
		log.info("memInfo ===> " + memInfo);
		
		if (memInfo == null) {
			return "redirect:/member/login";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", memInfo);
			service.updateLogDate(memInfo.getId());
			
			log.info("session......................." + session.getAttribute("loginInfo").toString());
		}
		model.addAttribute("memInfo", memInfo);
		return "redirect:/main";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		log.info("logout ..............");
		Object obj = session.getAttribute("loginInfo");
		
		if (obj != null) {
			session.removeAttribute("loginInfo");
			session.invalidate();
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join(){
		log.info("join ..............");
	}
	
	@PostMapping(value = "/joinPost")
	public String joinPost(MemberDTO mDto, HttpServletRequest request, Model model, RedirectAttributes rttr) {
		log.info("mDto......................." + mDto);
		
		MemberDTO memInfo = service.insertMember(mDto);
		
		if (memInfo == null) {
			return "redirect:/member/join";
		} else {
			
		}
		model.addAttribute("memInfo", memInfo);
		return "redirect:/main";
	}
	
	
	
	
	@RequestMapping(value = "/pwcheck", method = RequestMethod.GET)
	public void pwcheck(){
		log.info("pwcheck ..............");
	}
	
	@PostMapping(value = "/pwcheckPost")
	public String pwcheckPost(MemberDTO mDto, HttpServletRequest request, Model model, RedirectAttributes rttr) {
		log.info("mDto......................." + mDto);
		
		MemberDTO memInfo = service.login(mDto);
		log.info("memInfo ===> " + memInfo);
		
		if (memInfo == null) {
			return "redirect:/member/pwcheck";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("loginInfo", memInfo);
			
			log.info("session......................." + session.getAttribute("loginInfo").toString());
		}
		model.addAttribute("memInfo", memInfo);
		return "redirect:/pwchecknext";
		
	}
	
	@RequestMapping(value = "/pwcheckNext", method = RequestMethod.POST)
	public String sendMail(HttpServletRequest request, HttpServletRequest response, Model model) throws Exception {
		String title = "메일 테스트 중입니다.";
		String from = "dain7362@naver.com";
		String text = "메일 테스트";
		String to = "dain7362@gmail.com";
		String cc = "";
		
		log.info(title);
		
		MailUtil.mailSend(title, from, text, to, cc);
		return "redrect:/pwcheck";
	}
}