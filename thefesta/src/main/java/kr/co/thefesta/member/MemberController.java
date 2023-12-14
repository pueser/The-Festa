package kr.co.thefesta.member;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController; // RestController
import org.springframework.web.multipart.MultipartFile;

import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.service.IMemberService;
import kr.co.thefesta.member.until.MailUtil;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*")
@Log4j
public class MemberController {

	@Autowired
	private IMemberService service;
	
	@PostMapping(value = "/loginPost") // o
	public MemberDTO loginPost(@RequestBody MemberDTO mDto, HttpSession session) throws Exception {
		MemberDTO memInfo = service.login(mDto);
		log.info("MemberDTO ==> " + memInfo);
		
		if (memInfo != null) {
			session.setAttribute("loginInfo", memInfo);
			log.info("session......" + session.getAttribute("loginInfo").toString());
		}
		return memInfo;
	}

	
	@GetMapping("/logout")
	public ResponseEntity<String> logout(@RequestParam String id, HttpSession session) {
	    log.info("logout......");
	    log.info(id);

	    try {
	        service.updateFinalaccess(id);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        log.error("로그아웃 중 오류 발생: " + e.getMessage());
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	
	
	@RequestMapping(value = "/nicknameCheck", method = RequestMethod.POST)
    public String nicknameCheck(@RequestBody MemberDTO mDto) throws Exception {
        String nickname = mDto.getNickname();
		int nicknameCheck = service.nicknameCheck(nickname);
        String nickCheckResult = "fail";
        
        if(nicknameCheck != 0) {
        	log.info("fail");
        	return nickCheckResult;

		} else {
			log.info("success");
			nickCheckResult = "success";
			return nickCheckResult;
		}
    }
	
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String idCheck(@RequestBody MemberDTO mDto) throws Exception {
		String id = mDto.getId();
		int idCheck = service.idCheck(id);
		String idResult = "fail";
		
		if(idCheck != 0) {
			log.info("fail");
			return idResult;
			
		} else {
			log.info("success");
			idResult = "success";
			return idResult;
		}
	}
	
	@RequestMapping(value = "/selMember", method = RequestMethod.POST)
	public MemberDTO selMember(@RequestBody MemberDTO mDto) throws Exception {
		String id = mDto.getId();
		MemberDTO selMember = service.selMember(id);
		log.info("selMember" + selMember);
		return selMember;
	}
	
	@RequestMapping(value = "/mailSend", method = RequestMethod.POST)
	public String sendMail(@RequestBody MemberDTO mDto) throws Exception {
		String randomCode = randomCode();
		String id = mDto.getId();
		
		String title = "TheFesta 인증번호 전송";
		String from = "festa1228@naver.com";
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
	public void joinPost(@RequestBody MemberDTO mDto) throws Exception {
		service.join(mDto);			
	}
	
	
	@PostMapping(value = "/pwReset")
	public void pwReset(@RequestBody MemberDTO mDto) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		
		String id = mDto.getId();
		String password = mDto.getPassword();
		
		paramMap.put("id", id);
		paramMap.put("password", password);
		
		service.pwReset(paramMap);
	}
	
	
	
	@PostMapping(value = "/memInfoReset")
	public ResponseEntity<String> memInfoReset(@RequestBody MemberDTO mDto) {
		log.info(mDto);
	    try {
	        service.memInfoReset(mDto);
	        return ResponseEntity.ok("success");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("fail");
	    }
	}
	

	@PostMapping("/updateImg")
	public String updateImg(@RequestParam String id, @RequestParam MultipartFile file) throws Exception {
	    log.info("id" + id);

	    Map<String, Object> paramMap = new HashMap<>();

	    if (!file.isEmpty()) {
	        String saveImg = "C:\\Users\\dain7\\git\\TheFesta\\thefesta\\src\\main\\webapp\\resources\\fileUpload\\" + file.getOriginalFilename();
	        log.info("파일 저장" + saveImg);
	        file.transferTo(new File(saveImg));

	        File savedFile = new File(saveImg);
	        if (savedFile.exists()) {
	            log.info("파일이 성공적으로 저장되었습니다.");

	            String profileImg = "http://localhost:9090/resources/fileUpload/" + file.getOriginalFilename();
	            log.info("테스트" + profileImg + id);
	            paramMap.put("profileImg", profileImg);
	            paramMap.put("id", id);
	            service.updateImg(paramMap);
	            log.info(paramMap);

	            return "success";
	        }
	    }
	    return "fail";
	}
	
	
	@PostMapping(value = "/updateState")
	public void updateState(@RequestBody MemberDTO mDto) throws Exception {
		
		service.updateState(mDto);
		log.info(mDto);
	}
}