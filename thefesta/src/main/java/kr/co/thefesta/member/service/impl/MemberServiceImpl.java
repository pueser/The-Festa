package kr.co.thefesta.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.mapper.MemberMapper;
import kr.co.thefesta.member.service.IMemberService;

@Service
public class MemberServiceImpl implements IMemberService {
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public MemberDTO login(MemberDTO mDto) {
		return mapper.login(mDto);
	}

	@Override
	public void logout(String id) {
		mapper.logout(id);
	}

	@Override
	public MemberDTO insertMember(MemberDTO mDto) {
		return mapper.insertMember(mDto);
	}

	@Override
	public void updateLogDate(String id) {
		mapper.updateLogDate(id);
		
	}

}