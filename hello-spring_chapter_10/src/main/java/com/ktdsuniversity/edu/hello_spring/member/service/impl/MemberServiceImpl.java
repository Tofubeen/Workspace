package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import java.lang.reflect.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;

	@Override
	public boolean doCreateNewMember(MemberVO memberVO) {
		
		int success = this.memberDao.createNewMember(memberVO);
		
		return success > 0;
	}

	@Override
	public boolean checkAvailableEmail(String email) {
		// TODO Auto-generated method stub
		return this.memberDao.selectEmailCount(email) == 0;
	}

	@Override
	public int checkEmailCount() {
		int success = this.memberDao.countEmail();
		return success;
	}
}
