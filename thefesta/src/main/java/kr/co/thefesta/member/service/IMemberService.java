package kr.co.thefesta.member.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.co.thefesta.member.domain.MemberDTO;

public interface IMemberService {

	public MemberDTO selMember(String id) throws Exception;
	public MemberDTO login(MemberDTO mDto) throws Exception;
	public void updateFinalaccess(String id) throws Exception;

	public int nicknameCheck(String nickname) throws Exception;
	public int idCheck(String id) throws Exception;
	public void join(MemberDTO mDto) throws Exception;
	public void reJoin(MemberDTO mDto) throws Exception;
	
	public void logout(String id) throws Exception;
	
	public void pwReset(Map<String, Object> paramMap);

	public void memInfoReset(MemberDTO mDto);

	public void updateImg(Map<String, Object> paramMap);
	public void updateState(MemberDTO mDto);
}
