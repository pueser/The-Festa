package kr.co.thefesta.scheduler.service;

import java.util.List;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.scheduler.domain.SchedulerCri;

public interface ISchedulerService {
	public int getFestaCnt(SchedulerCri sCri) throws Exception;
	public List<FestivalDTO> getFestaList(SchedulerCri sCri) throws Exception;
	public List<AreaCodeDTO> getDistrictSelectOptions(AreaCodeDTO acDTO) throws Exception;
}
