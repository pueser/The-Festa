package kr.co.thefesta.festival.service;

import java.util.List;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.LikeDTO;

public interface IFestivalService {
	public void insertApi(FestivalDTO fDto) throws Exception;
	public void insertImg(FestivalImageDTO fiDto) throws Exception;
	public void insertAreaCode(AreaCodeDTO aDto) throws Exception;
	public int insertLike(LikeDTO lDto) throws Exception;
	public boolean deleteLike(LikeDTO lDto) throws Exception;
	public List<LikeDTO> searchLike(LikeDTO lDto) throws Exception;
	public List<FestivalDTO> listAll(Criteria cri) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
	public boolean updateFestival(FestivalDTO fDto) throws Exception;
	public int searchImg(String serialnum) throws Exception;
	public List<AreaCodeDTO> getAreaCode() throws Exception;
	public List<FestivalImageDTO> getImg(String contentid) throws Exception;
}
