package com.ktdsuniversity.edu.hello_spring.member.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
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
		
		return this.getSqlSession().selectOne("com.ktdsuniversity.edu.hello_spring.member.dao.MemberDao.selectEmailCount" , email);
	}

	@Override
	public int countEmail() {
		
		return this.getSqlSession().selectOne(NAMESPACE +  ".countEmail");
	}

	@Override
	public String selectSalt(String email) {
		
		return this.getSqlSession().selectOne( NAMESPACE + ".selectSalt" , email);
	}

	@Override
	public MemberVO selectOneMember(LoginMemberVO loginMemberVO) {

		return this.getSqlSession().selectOne(NAMESPACE + ".selectOneMember" , loginMemberVO);
	}
}
