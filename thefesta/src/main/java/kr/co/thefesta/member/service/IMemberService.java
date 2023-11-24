package kr.co.thefesta.member.service;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IMemberService {

	public MemberDTO selMember(MemberDTO mDto) throws Exception;
	public MemberDTO login(MemberDTO mDto) throws Exception;
	public void updateLogDate(String id) throws Exception;

	public String stateCodeCheck(String nickname) throws Exception;
	public int nicknameCheck(String nickname) throws Exception;
	public int idCheck(String id) throws Exception;
	public void join(MemberDTO mDto) throws Exception;
	public void reJoin(MemberDTO mDto) throws Exception;
	
	public void logout(String id) throws Exception;
	
	public void updateState(String id, String statecode) throws Exception;
}
