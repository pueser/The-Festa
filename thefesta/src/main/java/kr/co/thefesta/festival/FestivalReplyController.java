package kr.co.thefesta.festival;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.PageDTO;
import kr.co.thefesta.festival.domain.ReplyPageDTO;
import kr.co.thefesta.festival.service.IFestivalReplyService;
import kr.co.thefesta.festival.service.IFestivalService;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/festival")
@CrossOrigin(origins = "*")
public class FestivalReplyController {
	@Autowired
	private IFestivalReplyService service;
	
	@PostMapping(value = "/insertReply")
	public ResponseEntity<String> create(@RequestBody FestivalReplyDTO rDto) throws Exception {
		log.info("create---------------------");
		
		log.info("replyDto ===> " + rDto);
		
		int insertResult = service.register(rDto);
		
		log.info("Reply INSERT Result : " + insertResult);
		
		return insertResult == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/reply")
	public ResponseEntity<Map<String, Object>> getList(@RequestParam(defaultValue = "1") int page, @RequestParam String contentid) {
		log.info("getList-------------------");
		Map<String, Object> response = new HashMap<>();
		
		Criteria cri = new Criteria(page, 10);
		
		List<FestivalReplyDTO> frList = service.getListPage(cri, contentid);
		int total = service.getCountByContentid(contentid);
		
		response.put("list", frList);
		response.put("pageMaker", new PageDTO(cri, total));
		
		log.info("cri : " + cri);
		log.info("frList : " + frList);
		log.info("total : " + total);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PatchMapping("/reply/modify")
	public ResponseEntity<String> replyModify(@RequestBody FestivalReplyDTO frDto) throws Exception {
		log.info("replyModify----------------");
		
		log.info("frDto : " + frDto);
		
		boolean result = service.updateReply(frDto);
		
		return result ? new ResponseEntity<>("success", HttpStatus.OK)
				   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PatchMapping("/reply/delete")
	public ResponseEntity<String> replyDelete(@RequestParam int frno) throws Exception {
		log.info("replyDelete----------------");
		
		log.info("frno : " + frno);
		
		boolean result = service.deleteReply(frno);
		
		return result ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
