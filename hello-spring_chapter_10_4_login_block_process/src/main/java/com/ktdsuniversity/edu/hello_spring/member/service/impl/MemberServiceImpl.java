package com.ktdsuniversity.edu.hello_spring.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.access.dao.AccessLogDao;
import com.ktdsuniversity.edu.hello_spring.access.vo.AccessLogVO;
import com.ktdsuniversity.edu.hello_spring.common.beans.Sha;
import com.ktdsuniversity.edu.hello_spring.common.utils.RequestUtils;
import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private AccessLogDao accessLogDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private Sha sha;

	@Override
	public boolean doCreateNewMember(MemberVO memberVO) {
		
//		int emailCount = this.memberDao.selectEmailCount(memberVO.getEmail());
//		if(emailCount > 0) {throw new illegalArgumentException }
		
		//1. salt발급
		String salt = sha.generateSalt();
		
		//2. 사용자의 비밀번호 암호화
		String password = memberVO.getPassword();
		
		password = sha.getEncrypt(password, salt);
		memberVO.setSalt(salt);
		memberVO.setPassword(password);
		
		int success = this.memberDao.createNewMember(memberVO);
		
		
		
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
		
		boolean isIpBlock = this.accessLogDao.selectLoginFailCount(RequestUtils.getIp()) >=5 ;
		
		if(isIpBlock) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		
		//1.Salt 조회
		String salt = this.memberDao.selectSalt(loginMemberVO.getEmail()); //DB에서 Salt 가져옴
		
		if(salt == null) { // 이메일과 일치하는 Salt 가 없다면 이메일이 존재하지않는것
			
			AccessLogVO accessLogVO = new AccessLogVO();
			accessLogVO.setAccessType("LOGIN");
			accessLogVO.setAccessUrl(RequestUtils.getRequest().getRequestURI());
			accessLogVO.setAccessMethod(RequestUtils.getRequest().getMethod().toUpperCase());
			accessLogVO.setAccessIp(RequestUtils.getIp());
			this.accessLogDao.insertNewAccessLog(accessLogVO);
			
			throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
		}
		
		//2. 사용자가 입력한 비밀번호를 Salt를 이용해 암호화
		String password = loginMemberVO.getPassword();
		password = this.sha.getEncrypt(password, salt); // password에 로그인정보에 입력한 비밀번호를 salt로 암호화
		loginMemberVO.setPassword(password);
		
		
		//3. 이메일과 암호화된 비밀번호로 데이터베이스에서 회원 정보 조회
		MemberVO memberVO = this.memberDao.selectOneMember(loginMemberVO);
		if(memberVO == null) {
			
			//LOGIN_FAIL_COUNT 를 하나 증가시킨다
			//LATEST_LOGIN_FAIL_DATE 를 현재 날짜로 갱신한다
			//LATEST_LOGIN_IP 를 요청자의 IP로 갱신한다
			loginMemberVO.setIp(RequestUtils.getIp());
			this.memberDao.updateLoginFailState(loginMemberVO);
			throw new IllegalArgumentException("이메일 혹은 비밀번호가 일치하지 않습니다.");
		}
		
		//LOGIN_FAIL_COUNT 가 5이상 && 마지막 로그인 실패 시간이 한시간이 지나지 않았다면  
		//정상적인 로그인 시도라고 하더라도 로그인은 실패시켜야 한다
		boolean isBlockState = this.memberDao.selectLoginImpossibleCount(loginMemberVO.getEmail()) > 0;
		
		if(isBlockState) {
			throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
		}
		
		//LOGIN_FAIL_COUNT 가 5이상 && 마지막 로그인 실패 시간이 한시간이 지났다면
		//혹은 LOGIN_FAIL_COUNT가 5미만 일 경우
		//정상적인 로그인 시도일 경우 로그인을 성공 시킨다
		//이때 LOGIN_FAIL_COUNT 는 0으로 초기화 시키고
		//LATEST_LOGIN_FAIL_DATE 는 NULL로 초기화
		//LATEST_LOGIN_IP 는 요청자의 IP로 갱신 
		//LATEST_LOGIN_SUCCESS_DATE 는 현재 날짜로 갱신
		loginMemberVO.setIp(RequestUtils.getIp()); // 요청자의 IP주소를 가져와서 현재 로그인을 할려는 유저의 정보에 넣어준다(요청자의 IP 주소가 달라질수도 있기도하고 상태가 변할때마다 사용자의 IP주소도 매번 갱신되어야하니까?)
		this.memberDao.updateLoginSuccessState(loginMemberVO);
		
		AccessLogVO accessLogVO = new AccessLogVO();
		accessLogVO.setAccessType("LOGIN");
		accessLogVO.setAccessEmail(loginMemberVO.getEmail());
		accessLogVO.setAccessUrl(RequestUtils.getRequest().getRequestURI());
		accessLogVO.setAccessMethod(RequestUtils.getRequest().getMethod().toUpperCase());
		accessLogVO.setAccessIp(RequestUtils.getIp());
		accessLogVO.setLoginSuccessYn("Y");
		this.accessLogDao.insertNewAccessLog(accessLogVO);
		
		return memberVO;
	}

	@Override
	public boolean deleteMe(String email) {
		int success = this.memberDao.deleteMe(email);
		System.out.println("탈퇴한 인원수는 " + success);
		return success > 0;
	}
}
