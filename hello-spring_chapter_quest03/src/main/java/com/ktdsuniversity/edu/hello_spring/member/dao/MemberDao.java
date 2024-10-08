package com.ktdsuniversity.edu.hello_spring.member.dao;

import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;

public interface MemberDao {

	public String NAMESPACE = "com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao";
	
	
	public int selectEmailCount(String email);
	
	public int createNewMember(RegistMemberVO registMemberVO);
	
	public int countEmail();
	
	public String selectSalt(String id);
	
	public MemberVO selectOneMember(LoginMemberVO loginMemberVO);
}
