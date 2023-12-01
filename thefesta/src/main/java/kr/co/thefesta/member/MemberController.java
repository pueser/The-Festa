package kr.co.thefesta.member;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController; // RestController
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	
	private String uploadPath = "D:\\workspace\\spring4-4.10.0.RELEASE\\thefestaTest\\src\\main\\webapp\\resources\\fileUpload";
	
	@PostMapping(value = "/loginPost") // o
	public MemberDTO loginPost(@RequestBody MemberDTO mDto, HttpSession session) throws Exception {
		MemberDTO memInfo = service.login(mDto);
		log.info("MemberDTO ==> " + memInfo);
		
		if (memInfo == null) {
//			return "redirect:/member/login"; 리액트에서 처리
		} else {
			session.setAttribute("loginInfo", memInfo);
			log.info("session......" + session.getAttribute("loginInfo").toString());
//			return "redirect:/member/login"; 리액트에서 처리
		}
		return memInfo;
	}

	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) throws Exception {
		log.info("logout......");
		Object obj = session.getAttribute("loginInfo");
		
		if (obj != null) {
			
			MemberDTO memInfo = (MemberDTO) obj;
	        String id = memInfo.getId();
	        
			service.updateLogDate(id);
			
			session.removeAttribute("loginInfo");
			session.invalidate();
//			log.info("sessionRemove......" + session.getAttribute("loginInfo").toString()); 세션 삭제 확인용
			return ResponseEntity.ok("success");
		}
		
		return ResponseEntity.status(401).body("fail");
	}
	
	
	
	@PostMapping("/join")
	public void join(@RequestBody MemberDTO mDto){
		log.info("join......" + mDto);
		
	}
	
	@RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
    public ResponseEntity<String> nicknameCheck(@RequestBody String nickname) throws Exception {
        int nicknameCheck = service.nicknameCheck(nickname);
//        String stateCodeCheck = service.stateCodeCheck(nickname);
        String nickCheckResult = "fail";
        
        if(nicknameCheck != 0) {
        	log.info("fail");
        	return ResponseEntity.status(409).body("fail");

		} else {
			log.info("success");
			nickCheckResult = "success";
			return ResponseEntity.ok("success");
		}
    }
	
	// 멤버 상태코드를 조회해 아이디 중복검사 진행
	@RequestMapping(value = "/selMember", method = RequestMethod.POST)
	public String selMember(@RequestBody String id) throws Exception {
		MemberDTO selMember = service.selMember(id);
		String stateCode = selMember.getStatecode();
//		int idCheck = service.idCheck(id);
		
		return stateCode;
	}
	
	@RequestMapping(value = "/mailSend", method = RequestMethod.POST)
	public String sendMail(@RequestBody String id) throws Exception {
		String randomCode = randomCode();
		
		String title = "TheFesta 인증번호 전송";
		String from = "dain7362@naver.com";
		String text = "인증번호는 " + randomCode + " 입니다.";
		String to = id;
		String cc = "";
		
		MailUtil.mailSend(title, from, text, to, cc);
		return randomCode;
	}
	
	private String randomCode() {
        Random random = new Random();
        int randomCode = 100000 + random.nextInt(900000);
        return String.valueOf(randomCode);
    }
	
	@PostMapping(value = "/joinPost")
	public String joinPost(@RequestBody MemberDTO mDto) throws Exception {
		service.join(mDto);			
		
		return "redirect:/member/login"; // 수정 필요
	}
	
	
	
	@PostMapping(value = "/pwReset")
	public String pwReset(@RequestBody MemberDTO mDto) throws Exception {
		
		String id = mDto.getId();
		String password = mDto.getPassword();
		
		service.pwReset(id, password);
		
		return ""; // 수정 필요
	}
	
	
	
	@PostMapping(value = "/memInfoReset")
	public String memInfoReset(@RequestBody MemberDTO mDto, HttpSession session) throws Exception {
		
		log.info(mDto);
		
		if (mDto.getNickname() == null) {
			String nickname = (String) session.getAttribute("nickname");
			mDto.setNickname(nickname);
	    }
		if (mDto.getResetPassword() != null) {
			String resetPassword = (String) session.getAttribute("resetPassword");
			mDto.setResetPassword(resetPassword);
		}
		service.memInfoReset(mDto);
		return ""; // 수정 필요
	}
	

	@PostMapping("/editProfilePost")
	public String addFile(@RequestParam String id, @RequestParam MultipartFile file) throws Exception {
		log.info("id" + id);
		
		Map<String, Object> paramMap = new HashMap<>();
		
		if (!file.isEmpty()) {
			String profileImg = "/D:\\workspace\\spring4-4.10.0.RELEASE\\thefestaTest\\src\\main\\webapp\\resources\\fileUpload\\" + file.getOriginalFilename();
			log.info("파일 저장" + profileImg);
			file.transferTo(new File(profileImg));
			
			log.info("테스트" + profileImg + id);
			paramMap.put("profileImg", profileImg);
			paramMap.put("id", id);
			
			service.updateImg(paramMap);
		}
		
		return "member/register";
	}
	
	
	
	
//	@PostMapping(value = "/memInfoReset")
//	public String memInfoReset(@RequestBody MemberDTO mDto) throws Exception {
//		
//		MemberDTO resetData = service.memInfoReset(mDto);
//		
//		if (mDto.getNickname() != null) {
//			resetData.setNickname(resetData.getNickname());
//		}
//		if (mDto.getProfileImg() != null) {
//			resetData.setProfileImg(resetData.getProfileImg());
//		}
//		if (mDto.getPassword() != null) {
//			resetData.setPassword(resetData.getPassword());
//		}
//		return ""; // 수정 필요
//	}
//	
//	@GetMapping(value = "/register")
//	public String main() {
//		log.info("infoview .............");
//		return "member/register";
//	}
//	
//	@PostMapping("/registerPost")
//	public String addFile(@RequestParam String id, @RequestParam MultipartFile file) throws Exception {
//		log.info("id" + id);
//		
//		Map<String, Object> paramMap = new HashMap<>();
//		
//		if (!file.isEmpty()) {
//			String profileImg = "/D:\\workspace\\spring4-4.10.0.RELEASE\\thefestaTest\\src\\main\\webapp\\resources\\fileUpload\\" + file.getOriginalFilename();
//			log.info("파일 저장" + profileImg);
//			file.transferTo(new File(profileImg));
//			
//			log.info("테스트" + profileImg + id);
//			paramMap.put("profileImg", profileImg);
//			paramMap.put("id", id);
//			
//			service.updateImg(paramMap);
//		}
//		
//		return "member/register";
//	}
	
	
	
	
	
	
	
	@PostMapping(value = "/updateState")
	public void updateState(@RequestBody MemberDTO mDto) throws Exception {
		String id = mDto.getId();
		String statecode = mDto.getStatecode();
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("statecode", statecode);
		
		service.updateState(paramMap);
		
		log.info(paramMap);
	}
}