package com.ktdsuniversity.edu.hello_spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.dao.impl.HelloBootDaoImpl;
import com.ktdsuniversity.edu.hello_spring.service.HelloBootService;

@Service
public class HelloBootServiceImpl implements HelloBootService{

	
	@Autowired
	private HelloBootDaoImpl helloBootDaoImpl;
	
	
	public HelloBootServiceImpl() {
		System.out.println("serviceImpl 인스턴스 생성");
	}
	
	
	@Override
	public String getGreetingMessage() {
		
		String message =  this.helloBootDaoImpl.selectMessage();
		
		return "안녕하세요 서비스 클래스 입니다." + message;
	}

}
