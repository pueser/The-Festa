package kr.co.thefesta.member.persistence;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IMemberDAO {

	public MemberDTO selMember(MemberDTO mDto);
	public MemberDTO login(MemberDTO mDto);
	public void updateLogDate(String id);
	public MemberDTO selLoginInfo(String id, String password) throws Exception;

	public String stateCodeCheck(String nickname);
	public int nicknameCheck(String nickname);
	public int idCheck(String id);
	public void join(MemberDTO mDto);
	public void reJoin(MemberDTO mDto);
	
	public void logout(String id);
	
	public void updateState(MemberDTO mDto);
}
