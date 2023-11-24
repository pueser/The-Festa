package kr.co.thefesta.scheduler.service;

import java.util.ArrayList;
import java.util.List;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;

public interface ISchedulerService {
	public int getFestaCnt(int date) throws Exception;
	public List<FestivalDTO> getFestaList(int date) throws Exception;
	public ArrayList<AreaCodeDTO> getDistrictSelectOptions(int acode) throws Exception;
}
