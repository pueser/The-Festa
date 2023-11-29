package kr.co.thefesta.festival;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
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
			List<FestivalDTO> fList = service.listAll(cri);
			int total = service.getTotalCnt(cri);
			
			response.put("list", fList);
			response.put("pageMaker", new PageDTO(cri, total));
			
			log.info("total : " + total);
			log.info("cri : " + cri);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("error", e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
