package com.ktdsuniversity.edu.hello_spring.member.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int createNewMember(MemberVO memberVO) {
		
		return this.getSqlSession().insert("com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao.createNewMember" , memberVO);
	}

	@Override
	public int selectEmailCount(String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countEmail() {
		
		return this.getSqlSession().selectOne("com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao.countEmail");
	}
}
