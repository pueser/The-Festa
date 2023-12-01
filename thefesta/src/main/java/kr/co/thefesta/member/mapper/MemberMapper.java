package kr.co.thefesta.member.mapper;

import java.util.Map;

import kr.co.thefesta.member.domain.MemberDTO;

public interface MemberMapper {

	public MemberDTO selMember(String id);
	public MemberDTO login(MemberDTO mDto);
	public void updateLogDate(String nickname);

	public String stateCodeCheck(String stateCode);
	public int nicknameCheck(String nickname);
	public int idCheck(String id);
	public void join(MemberDTO mDto);
	public void reJoin(MemberDTO mDto);
	
	public void logout(String id);
	
	public void pwReset(String id, String password);
	
	public void memInfoReset(MemberDTO mDto);

	public void updateImg(Map<String, Object> paramMap);
	
	public void updateState(Map<String, Object> paramMap);
}