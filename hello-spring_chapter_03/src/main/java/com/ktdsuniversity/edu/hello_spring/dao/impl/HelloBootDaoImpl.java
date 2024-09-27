package com.ktdsuniversity.edu.hello_spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.hello_spring.dao.HelloBootDao;


// Dao 는 Repository 에 해당
/**
 * 데이터 베이스와 통신을 수행하는 클래스
 * @service 가 관리하는 클래스
 *  --> @service가 @repository 에 대해서 트랜젝션을 수행
 */
@Repository
public class HelloBootDaoImpl implements HelloBootDao{
	
	
	public HelloBootDaoImpl() {
		System.out.println("HelloBootDaoimpl 인스턴스 생성함.");
	}
	
	
	@Override
	public String selectMessage() {
		
		return "반갑습니다.";
	}

	
}
