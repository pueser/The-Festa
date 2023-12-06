package kr.co.thefesta.member.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.member.domain.MemberDTO;
import kr.co.thefesta.member.persistence.IMemberDAO;
import kr.co.thefesta.member.service.IMemberService;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	private IMemberDAO mDao;

	@Override
	public MemberDTO selMember(String id) throws Exception {
		return mDao.selMember(id);
	}

	@Override
	public MemberDTO login(MemberDTO mDto) throws Exception {
		return mDao.login(mDto);
	}

	@Override
	public void updateFinalaccess(String id) throws Exception {
		mDao.updateFinalaccess(id);
	}

	@Override
	public int nicknameCheck(String nickname) throws Exception {
		log.info(nickname);
		return mDao.nicknameCheck(nickname);
	}

	@Override
	public int idCheck(String id) throws Exception {
		return mDao.idCheck(id);
	}

	@Override
	public void join(MemberDTO mDto) throws Exception {
		mDao.join(mDto);
	}

	@Override
	public void reJoin(MemberDTO mDto) throws Exception {
		mDao.reJoin(mDto);
	}

	@Override
	public void logout(String id) throws Exception {
		mDao.logout(id);
	}

	@Override
	public void pwReset(Map<String, Object> paramMap) {
		log.info("도착" + paramMap);
		mDao.pwReset(paramMap);
	}

	@Override
	public void memInfoReset(MemberDTO mDto) {
		mDao.memInfoReset(mDto);
	}

	@Override
	public void updateImg(Map<String, Object> paramMap) {
		mDao.updateImg(paramMap);
	}
	
	@Override
	public void updateState(MemberDTO mDto) {
		mDao.updateState(mDto);
	}
	
}