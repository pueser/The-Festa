package kr.co.thefesta.scheduler.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;

@MapperScan
public interface SchedulerMapper {
	public int getFestaCnt(int date) throws Exception;
	public List<FestivalDTO> getFestaList(int date) throws Exception;
	public ArrayList<AreaCodeDTO> getDistrictSelectOptions(int acode) throws Exception;
}
