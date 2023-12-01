package kr.co.thefesta.scheduler.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;

@MapperScan
public interface SchedulerMapper {
	public int getFestaCnt(@Param("date")int date, 
			@Param("countyValue")int countyValue, 
			@Param("districtValue")int districtValue, 
			@Param("keyword")String keyword) throws Exception;
	public List<FestivalDTO> getFestaList(@Param("date")int date, 
			@Param("countyValue")int countyValue, 
			@Param("districtValue")int districtValue, 
			@Param("keyword")String keyword) throws Exception;
	public ArrayList<AreaCodeDTO> getDistrictSelectOptions(int acode) throws Exception;
}
