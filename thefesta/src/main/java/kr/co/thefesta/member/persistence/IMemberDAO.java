package kr.co.thefesta.member.persistence;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IMemberDAO {

	public String getTime();
	public void insertMember(MemberDTO mDto);
	public MemberDTO selMember(String id) throws Exception;
	public MemberDTO selLoginInfo(String id, String password) throws Exception;
	public void updateLogDate(String id);
}
