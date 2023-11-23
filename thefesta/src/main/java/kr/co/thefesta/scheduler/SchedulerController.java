package kr.co.thefesta.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.scheduler.service.ISchedulerService;
import lombok.extern.log4j.Log4j;

@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping("/schduler")
@Log4j
public class SchedulerController {
	
	@Autowired
	private ISchedulerService service;
	
	@RequestMapping(value = "/festaCnt", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getFestaCnt(@RequestParam("date") Integer date) throws Exception {
		log.info("getFestaCnt....................");
		log.info("RequestParam : " + date);
		Map<String, Integer> result = new HashMap<>();
		Integer festaCnt = Integer.valueOf(service.getFestaCnt(date));
		log.info("festaCnt : " + festaCnt);
		result.put("festaCnt", festaCnt);
		log.info("controllerResult : " + result);
		return result;
	}
	
	@RequestMapping(value = "/festaList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Integer> getFestaList(@RequestParam("date") Integer date) throws Exception {
		log.info("getFestaList....................");
		log.info("RequestParam : " + date);
		Map<String, Integer> result = new HashMap<>();
		return result;
	}
}
