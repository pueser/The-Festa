package kr.co.thefesta.festival.service;

import java.util.List;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;

public interface IFestivalService {
	public void insertApi(FestivalDTO fDto) throws Exception;
	public void insertImg(FestivalImageDTO fiDto) throws Exception;
	public void insertAreaCode(AreaCodeDTO aDto) throws Exception;
	public List<FestivalDTO> readFestival(String startdate, String enddate) throws Exception;
	public List<FestivalDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
}
