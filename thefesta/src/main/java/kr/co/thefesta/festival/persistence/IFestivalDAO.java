package kr.co.thefesta.festival.persistence;

import java.util.List;

import kr.co.thefesta.festival.domain.AreaCodeDTO;
import kr.co.thefesta.festival.domain.Criteria;
import kr.co.thefesta.festival.domain.FestivalDTO;
import kr.co.thefesta.festival.domain.FestivalImageDTO;
import kr.co.thefesta.festival.domain.FestivalReplyDTO;
import kr.co.thefesta.festival.domain.LikeDTO;
import kr.co.thefesta.festival.domain.api.ItemDTO;

public interface IFestivalDAO {
	public void insertApi(FestivalDTO fDto) throws Exception;
	public void insertImg(FestivalImageDTO fiDto) throws Exception;
	public void insertAreaCode(AreaCodeDTO aDto) throws Exception;
	public int insertLike(LikeDTO lDto) throws Exception;
	public int deleteLike(LikeDTO lDto) throws Exception;
	public int searchLike(LikeDTO lDto) throws Exception;
	public List<LikeDTO> LikeList(Criteria cri, String id) throws Exception;
	public int getCountByLike(String id);
	public List<FestivalDTO> listAll(Criteria cri) throws Exception;
	public List<FestivalDTO> getList(String keyword, int today) throws Exception;
	public int getTotalCnt(Criteria cri) throws Exception;
	public int updateFestival(FestivalDTO fDto) throws Exception;
	public int searchImg(String serialnum) throws Exception;
	public List<AreaCodeDTO> getAreaCode() throws Exception;
	public List<FestivalImageDTO> getImg(String contentid) throws Exception;
	public FestivalDTO getFestival(String contentid) throws Exception;
}
