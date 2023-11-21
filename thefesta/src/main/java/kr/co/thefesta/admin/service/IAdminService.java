package kr.co.thefesta.admin.service;

import java.util.List;

import kr.co.thefesta.admin.domain.MemberDTO;



public interface IAdminService {
	//member 테이블 회원정보 list
	public List<MemberDTO> memberList()throws Exception;
	//member 회원 디테일 정보
	public MemberDTO memberDetail(String nickname)throws Exception;
}
