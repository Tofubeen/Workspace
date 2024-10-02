package com.ktdsuniversity.edu.hello_spring.member.service;

import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

public interface MemberService {

	public boolean doCreateNewMember(MemberVO memberVO);
	
	public boolean checkAvailableEmail(String email);
	
	public int checkEmailCount();
	
}
