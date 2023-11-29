package kr.co.thefesta.festival.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.persistence.IFestivalDAO;
import kr.co.thefesta.festival.service.IFestivalService;

@Service("festivalService")
public class FestivalServiceImpl implements IFestivalService {
	
	@Autowired
	private IFestivalDAO fDao;

	@Override
	public void insertApi(FestivalDTO fDto) throws Exception {
		fDao.insertApi(fDto);
		
	}

	@Override
	public void insertImg(FestivalImageDTO fiDto) throws Exception {
		fDao.insertImg(fiDto);
	}

	@Override
	public void insertAreaCode(AreaCodeDTO aDto) throws Exception {
		fDao.insertAreaCode(aDto);
	}

	@Override
	public List<FestivalDTO> readFestival(String startdate, String enddate) throws Exception {
		return fDao.readFestival(startdate, enddate);
	}

	@Override
	public List<FestivalDTO> listAll(Criteria cri) throws Exception {
		return fDao.listAll(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return fDao.getTotalCnt(cri);
	}

}
