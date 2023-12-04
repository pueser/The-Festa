package kr.co.thefesta.festival.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.LikeDTO;
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
	public List<FestivalDTO> listAll(Criteria cri) throws Exception {
		return fDao.listAll(cri);
	}

	@Override
	public int getTotalCnt(Criteria cri) throws Exception {
		return fDao.getTotalCnt(cri);
	}

	@Override
	public int searchImg(String serialnum) throws Exception {
		return fDao.searchImg(serialnum);
	}

	@Override
	public boolean updateFestival(FestivalDTO fDto) throws Exception {
		return fDao.updateFestival(fDto) == 1;
	}

	@Override
	public List<AreaCodeDTO> getAreaCode() throws Exception {
		return fDao.getAreaCode();
	}

	@Override
	public List<FestivalImageDTO> getImg(String contentid) throws Exception {
		return fDao.getImg(contentid);
	}

	@Override
	public int insertLike(LikeDTO lDto) throws Exception {
		return fDao.insertLike(lDto);
	}

	@Override
	public boolean deleteLike(LikeDTO lDto) throws Exception {
		return fDao.deleteLike(lDto) == 1;
	}

	@Override
	public List<LikeDTO> searchLike(LikeDTO lDto) throws Exception {
		return fDao.searchLike(lDto);
	}

}
