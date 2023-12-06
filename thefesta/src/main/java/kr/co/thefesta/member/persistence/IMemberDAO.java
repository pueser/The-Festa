package kr.co.thefesta.member.persistence;

import java.util.Map;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IMemberDAO {

	public MemberDTO selMember(String id);
	public MemberDTO login(MemberDTO mDto);
	public void updateLogDate(String id);
	public MemberDTO selLoginInfo(String id, String password) throws Exception;

	public int nicknameCheck(String nickname);
	public int idCheck(String id);
	public void join(MemberDTO mDto);
	public void reJoin(MemberDTO mDto);
	
	public void pwReset(Map<String, Object> paramMap);
	
	public void logout(String id);
	
	public void memInfoReset(MemberDTO mDto);
	
	public void updateImg(Map<String, Object> paramMap);
	
	public void updateState(MemberDTO mDto);
}
