package kr.co.thefesta.festival.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.persistence.IFestivalDAO;
import kr.co.thefesta.festival.service.IFestivalService;

@Service
public class FestivalServiceImpl implements IFestivalService {
	
	@Autowired
	private IFestivalDAO fDao;

	@Override
	public void insert(FestivalDTO fDto) throws Exception {
		fDao.insert(fDto);
		
	}

}
