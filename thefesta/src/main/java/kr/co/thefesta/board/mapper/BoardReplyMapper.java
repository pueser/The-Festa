package kr.co.thefesta.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.co.thefesta.board.domain.Criteria;
import kr.co.thefesta.board.domain.ReplyDTO;

public interface BoardReplyMapper {
		public String getTime2();
		public int insert(ReplyDTO replyDto);
		public ReplyDTO read(int brno);
		public int delete(int brno);
		public int update(ReplyDTO replyDto);
		public List<ReplyDTO> getListWithPaging(@Param("cri") Criteria cri,
												@Param("bid") int bid);
		public int getCountByBid(int bid);
	}

