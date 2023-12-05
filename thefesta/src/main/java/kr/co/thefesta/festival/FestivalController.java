package kr.co.thefesta.festival;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.LikeDTO;
import kr.co.thefesta.festival.domain.PageDTO;
import kr.co.thefesta.festival.service.IFestivalService;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/festival")
@CrossOrigin(origins = "*")
public class FestivalController {
	@Autowired
	private IFestivalService service;
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> listAll(Criteria cri) throws Exception {
		log.info("show all list..................");
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			log.info("cri----" + cri);
			List<FestivalDTO> fList = service.listAll(cri);
			log.info("fList----" + fList);
			int total = service.getTotalCnt(cri);
			List<AreaCodeDTO> aList = service.getAreaCode();
			
			response.put("list", fList);
			response.put("pageMaker", new PageDTO(cri, total));
			response.put("areaCode", aList);
			
			log.info("total : " + total);
			log.info("cri : " + cri);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/detail/{contentid}")
	public ResponseEntity<?> getFesivalDetail(@PathVariable String contentid) throws Exception {
		log.info("getFesivalDetail-------------------");
		log.info("contentid : " + contentid);
		
		try {
			List<FestivalImageDTO> fiList = service.getImg(contentid);
			
			log.info(fiList);
			
			return new ResponseEntity<>(fiList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/likeInsert")
	public ResponseEntity<String> likeInsert(@RequestBody LikeDTO lDto) throws Exception {
		log.info("likeInsert----------");
		
		log.info("lDto----------" + lDto);
		
		int result = service.insertLike(lDto);
		
		log.info("Like INSERT Result : " + result);
		
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
								   : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PostMapping("/likeDelete")
	public ResponseEntity<String> likeDelete(@RequestBody LikeDTO lDto) throws Exception {
		log.info("likeDelete----------");
		
		log.info("lDto----------" + lDto);
		
		boolean result = service.deleteLike(lDto);
		
		log.info("Like Delete Result : " + result);
		
		return result ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/likeSearch")
	public ResponseEntity<Integer> likeSearch(@RequestParam("contentid") String contentid,
		    @RequestParam("id") String id) throws Exception {
		log.info("likeSearch----------");
		
		log.info("contentid----------" + contentid);
		log.info("id----------" + id);
		LikeDTO lDto = new LikeDTO();
		lDto.setContentid(contentid);
		lDto.setId(id);
		
		try {
			int result = service.searchLike(lDto);
			
			log.info("Like List Result : " + result);
			
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
}
