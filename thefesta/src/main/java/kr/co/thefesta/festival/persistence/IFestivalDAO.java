package kr.co.thefesta.festival.persistence;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;

public interface IFestivalDAO {
	public void insertApi(FestivalDTO fDto) throws Exception;
	public void insertImg(FestivalImageDTO fiDto) throws Exception;
	public void insertAreaCode(AreaCodeDTO aDto) throws Exception;
}
