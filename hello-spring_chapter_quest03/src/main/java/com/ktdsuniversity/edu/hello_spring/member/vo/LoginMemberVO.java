package com.ktdsuniversity.edu.hello_spring.member.vo;

import jakarta.validation.constraints.NotBlank;

public class LoginMemberVO {

	
	@NotBlank(message = "아이디를 입력해주세요.")
	private String id;
	@NotBlank(message = "비밀번호를 입력해주세요.")
	private String password;
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
