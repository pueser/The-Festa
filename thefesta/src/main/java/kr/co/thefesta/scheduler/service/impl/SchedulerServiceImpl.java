package kr.co.thefesta.scheduler.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.scheduler.domain.SchedulerCri;
import kr.co.thefesta.scheduler.persistence.ISchedulerDAO;
import kr.co.thefesta.scheduler.service.ISchedulerService;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SchedulerServiceImpl implements ISchedulerService {
	@Autowired
	private ISchedulerDAO sDao;
	
	@Override
	public int getFestaCnt(SchedulerCri sCri) throws Exception {
		return sDao.getFestaCnt(sCri);
	}

	@Override
	public List<FestivalDTO> getFestaList(SchedulerCri sCri) throws Exception {
		return sDao.getFestaList(sCri);
	}

	@Override
	public List<AreaCodeDTO> getDistrictSelectOptions(AreaCodeDTO acDTO) throws Exception {
		log.info("getDistrictSelectOptionsServiceParam : " + acDTO);
		List<AreaCodeDTO> result = new ArrayList<>();
		result = sDao.getDistrictSelectOptions(acDTO);
		log.info("getDistrictSelectOptionsServiceReturen : " + result);
		return result;
	}
}
