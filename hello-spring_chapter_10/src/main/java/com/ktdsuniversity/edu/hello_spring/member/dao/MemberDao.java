package com.ktdsuniversity.edu.hello_spring.member.dao;

import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

public interface MemberDao {

	public int selectEmailCount(String email);
	
	public int createNewMember(MemberVO memberVO);
	
	public int countEmail();
	
	
}
