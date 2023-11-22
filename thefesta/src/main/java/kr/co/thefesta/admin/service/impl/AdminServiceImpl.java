package kr.co.thefesta.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.thefesta.admin.domain.ReportDTO;
import kr.co.thefesta.admin.persistence.IAdminDAO;
import kr.co.thefesta.admin.service.IAdminService;
import kr.co.thefesta.member.domain.MemberDTO;


@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	public IAdminDAO adminDao;
	
	@Override
	//member 테이블 회원정보 list
	public List<MemberDTO> memberList() throws Exception {
		return adminDao.memberList();
	}
	
	//member 회원 디테일 정보
	@Override
	public List<ReportDTO> memberDetail(String id) throws Exception {
		return adminDao.memberDetail(id);
	}

}
