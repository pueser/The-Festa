package kr.co.thefesta.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.scheduler.mapper.SchedulerMapper;
import kr.co.thefesta.scheduler.service.ISchedulerService;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SchedulerServiceImpl implements ISchedulerService {
	@Autowired
	private SchedulerMapper sMapper;
	
	@Override
	public int getFestaCnt(int date) throws Exception {
		int result = sMapper.getFestaCnt(date);
		log.info("serviceResult : " + result);
		return result;
	}
}
