package kr.co.thefesta.admin.persistence;

import java.util.List;

import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.member.domain.MemberDTO;

public interface IAdminDAO {
	//member 테이블 회원정보 list
	public List<MemberDTO> memberList()throws Exception;
	//member 회원 디테일 정보
	public List<ReportDTO> memberDetail(String id)throws Exception;
}
