package kr.co.thefesta.member.service.impl;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.http.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.mapper.MemberMapper;
import kr.co.thefesta.member.service.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public MemberDTO selMember(String id) throws Exception {
		return mapper.selMember(id);
	}
	
	@Override
	public MemberDTO login(MemberDTO mDto) throws Exception {
		return mapper.login(mDto);
	}

	@Override
	public void updateLogDate(String id) throws Exception {
		mapper.updateLogDate(id);
	}
	
	
	@Override
	public String stateCodeCheck(String nickname) throws Exception {
		return mapper.stateCodeCheck(nickname);
	}
	
	@Override
	public int nicknameCheck(String nickname) throws Exception {
		return mapper.nicknameCheck(nickname);
	}
	
	@Override
	public int idCheck(String id) throws Exception {
		return mapper.idCheck(id);
	}
	
	@Override
	public void join(MemberDTO mDto) throws Exception {
		mapper.join(mDto);
	}
	
	public void reJoin(MemberDTO mDto) throws Exception {
		mapper.reJoin(mDto);
	}


	@Override
	public void logout(String id) throws Exception {
		mapper.logout(id);
	}

	
	@Override
	public void pwReset(String id, String password) {
		mapper.pwReset(id, password);
	}
	
	@Override
	public void memInfoReset(MemberDTO mDto) {
		mapper.memInfoReset(mDto);
	}
	

	@Override
	public void updateImg(Map<String, Object> paramMap) {
		mapper.updateImg(paramMap);
		
	}

	@Override
	public void updateState(Map<String, Object> paramMap) {
		mapper.updateState(paramMap);
	}

}