package kr.co.thefesta.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.scheduler.domain.SchedulerCri;
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
	public Map<String, Integer> getFestaCnt(SchedulerCri sCri) throws Exception {
		log.info("getFestaCnt....................");
		log.info("RequestParam => sCri: " + sCri);
		Map<String, Integer> result = new HashMap<>();
		Integer festaCnt = Integer.valueOf(service.getFestaCnt(sCri));
		log.info("festaCnt : " + festaCnt);
		result.put("festaCnt", festaCnt);
		log.info("controllerResult : " + result);
		return result;
	}
	
	@RequestMapping(value = "/festaList", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<FestivalDTO>> getFestaList(SchedulerCri sCri) throws Exception {
		log.info("getFestaList....................");
		Map<String, List<FestivalDTO>> result = new HashMap<>();
		result.put("festaList", service.getFestaList(sCri));
		log.info("controllerResult : " + result);
		return result;
	}
	
	@RequestMapping(value = "/districtSelectOption", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, List<AreaCodeDTO>> getDistrictSelectOptions(AreaCodeDTO acDTO) throws Exception {
		log.info("getDistrictSelectOptions....................");
		log.info("acodeRequestParam : " + acDTO);
		Map<String, List<AreaCodeDTO>> result = new HashMap<>();
		result.put("districtList", service.getDistrictSelectOptions(acDTO));
		log.info("acodeControllerResult : " + result);
		return result;
	}
}
