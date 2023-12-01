package kr.co.thefesta.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.board.domain.BoardDTO;
import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.domain.PageDTO;
import kr.co.thefesta.board.service.IBoardService;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
@Log4j
public class BoardController {
	
	@Autowired
    private IBoardService service;
	

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> listAll(Criteria cri) throws Exception {
        log.info("show all list..................");
        Map<String, Object> result = new HashMap<>();
        result.put("list", service.listAll(cri));

        int total = service.getTotalCnt(cri);
        log.info(cri);
        log.info("total : " + total);

        
        result.put("pageMaker", new PageDTO(cri, total));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> registerPost(@RequestBody BoardDTO bDto) throws Exception {
        log.info("register post....................");

        log.info("register.........." + bDto);

        if (bDto.getAttachList() != null) {
            bDto.getAttachList().forEach(attach -> log.info(attach));
        }

        service.register(bDto);

        return new ResponseEntity<>("Board created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = {"/read", "/modify"}, method = RequestMethod.GET)
    public ResponseEntity<BoardDTO> modifyGET(@RequestParam("bid") int bid, @ModelAttribute("cri") Criteria cri) throws Exception {
        log.info("/read or /modify..............");

        BoardDTO board = service.read(bid);

        return new ResponseEntity<BoardDTO>(board, HttpStatus.OK);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<String> modifyPost(@RequestBody BoardDTO bDto, @ModelAttribute("cri") Criteria cri) throws Exception {
        log.info(bDto.toString());
    	log.info("modify post............." + service.modify(bDto));

        return new ResponseEntity<String>("Board modified successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResponseEntity<String> remove(@RequestParam("bid") int bid, @ModelAttribute("cri") Criteria cri) throws Exception {
        log.info("remove......................");

        service.remove(bid);

        return new ResponseEntity<>("Board removed successfully", HttpStatus.OK);
    }
    
 
    @PutMapping("/increaseViewCnt/{bid}")
    public ResponseEntity<String> increaseViewCnt(@PathVariable int bid) {
        try {
        	log.info(bid);
            service.increaseViewCnt(bid);
            return new ResponseEntity<>("View count increased successfully", HttpStatus.OK);
            
        } catch (Exception e) {
        	
            log.error("Failed to increase view count", e);
            return new ResponseEntity<>("Failed to increase view count", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
