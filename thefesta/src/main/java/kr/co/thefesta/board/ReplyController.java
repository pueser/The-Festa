package kr.co.thefesta.board;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.domain.ReplyDTO;
import kr.co.thefesta.board.service.IReplyService;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/replies")
@CrossOrigin(origins = "*")
@Log4j
public class ReplyController {

    @Autowired
    private IReplyService service;

    @PostMapping("/new")
    public ResponseEntity<String> create(@RequestBody ReplyDTO replyDto) {
        log.info("replyDto ===> " + replyDto);

        int insertResult = service.register(replyDto);
        int replyCntResult = service.replyCntUpdate(replyDto.getBid());

        log.info("Reply INSERT Result: " + insertResult);
        log.info("Reply Count Result: " + replyCntResult);

        return (insertResult == 1 && replyCntResult == 1)
                ? ResponseEntity.ok("success")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
    }

    @GetMapping("/pages/{bid}/{page}")
    public ResponseEntity<List<ReplyDTO>> getList(
            @PathVariable int page,
            @PathVariable int bid) {

        Criteria cri = new Criteria(page, 10);

        log.info("get Reply List bid : " + bid);
        log.info("cri :" + cri);

        return ResponseEntity.ok(service.getList(cri, bid));
    }

    @GetMapping("/{brno}")
    public ResponseEntity<ReplyDTO> read(@PathVariable int brno) {
        log.info("get : " + brno);

        return ResponseEntity.ok(service.read(brno));
    }

    @DeleteMapping("/{brno}")
    public ResponseEntity<String> remove(@PathVariable int brno) {
        return service.remove(brno) == 1
                ? ResponseEntity.ok("success")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value = "/{brno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDto, @PathVariable int brno) {
        replyDto.setBrno(brno);

        log.info("brno : " + brno);
        log.info("modify : " + replyDto);

        return service.modify(replyDto) == 1
                ? ResponseEntity.ok("success")
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    
    @PostMapping("/userReply")
    public ResponseEntity<?> userReply(@RequestBody ReplyDTO replyDto) {
        String id = replyDto.getId();
        log.info("받아 온 유저 데이터: " + id);

        List<ReplyDTO> userReplies = service.userReply(id);
        return ResponseEntity.ok(userReplies);
    }
    
}