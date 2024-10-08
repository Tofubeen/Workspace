package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.common.beans.Sha;
import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Sha sha;

	@Override
	public boolean doCreateNewMember(RegistMemberVO registMemberVO) {
		
		
		//1. salt발급
		String salt = sha.generateSalt();
		
		//2. 사용자의 비밀번호 암호화
		String password = registMemberVO.getPassword();
		
		password = sha.getEncrypt(password, salt);
		registMemberVO.setSalt(salt);
		registMemberVO.setPassword(password);
		
		int success = this.memberDao.createNewMember(registMemberVO);
		
		
		
		return success > 0;
	}

	@Override
	public boolean checkAvailableEmail(String email) {
		
		return this.memberDao.selectEmailCount(email) == 0;
	}

	@Override
	public int checkEmailCount() {
		int success = this.memberDao.countEmail();
		return success;
	}

	@Override
	public MemberVO readMemberVO(LoginMemberVO loginMemberVO) {
		
		//1.Salt 조회
		String salt = this.memberDao.selectSalt(loginMemberVO.getId()); //DB에서 Salt 가져옴
		
		if(salt == null) { // 이메일과 일치하는 Salt 가 없다면 이메일이 존재하지않는것
			throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
		}
		
		//2. 사용자가 입력한 비밀번호를 Salt를 이용해 암호화
		String password = loginMemberVO.getPassword();
		password = this.sha.getEncrypt(password, salt); // password에 로그인정보에 입력한 비밀번호를 salt로 암호화
		loginMemberVO.setPassword(password);
		
		
		//3. 이메일과 암호화된 비밀번호로 데이터베이스에서 회원 정보 조회
		MemberVO memberVO = this.memberDao.selectOneMember(loginMemberVO);
		if(memberVO == null) {
			throw new IllegalArgumentException("이메일 혹은 비밀번호가 일치하지 않습니다.");
		}
		
		return memberVO;
	}
}
