package kr.co.thefesta.scheduler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
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
		return sMapper.getFestaCnt(date);
	}

	@Override
	public List<FestivalDTO> getFestaList(int date) throws Exception {
		return sMapper.getFestaList(date);
	}

	@Override
	public ArrayList<AreaCodeDTO> getDistrictSelectOptions(int acode) throws Exception {
		log.info("getDistrictSelectOptionsServiceParam : " + acode);
		ArrayList<AreaCodeDTO> result = new ArrayList<>();
		result = sMapper.getDistrictSelectOptions(acode);
		log.info("getDistrictSelectOptionsServiceReturen : " + result);
		return result;
	}
}
