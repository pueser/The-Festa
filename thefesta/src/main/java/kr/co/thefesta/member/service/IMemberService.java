package kr.co.thefesta.member.service;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IMemberService {

	public MemberDTO login(MemberDTO mDto);
	public void logout(String id);
	public MemberDTO insertMember(MemberDTO mDto);
	public void updateLogDate(String id);
}
