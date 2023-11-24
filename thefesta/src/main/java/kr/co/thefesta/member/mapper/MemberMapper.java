package kr.co.thefesta.member.mapper;

import kr.co.thefesta.member.domain.MemberDTO;

public interface MemberMapper {

	public MemberDTO selMember(MemberDTO mDto);
	public MemberDTO login(MemberDTO mDto);
	public void updateLogDate(String nickname);

	public String stateCodeCheck(String stateCode);
	public int nicknameCheck(String nickname);
	public int idCheck(String id);
	public void join(MemberDTO mDto);
	public void reJoin(MemberDTO mDto);
	
	public void logout(String id);
	
	public void updateState(String id, String statecode);
}
