package kr.co.thefesta.festival.persistence;

import kr.co.thefesta.festival.domain.FestivalDTO;

public interface IFestivalDAO {
	public void insert(FestivalDTO fDto) throws Exception;
}
