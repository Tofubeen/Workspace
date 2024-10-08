package com.ktdsuniversity.edu.hello_spring.bbs.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.ToDoBoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoWriteBoardVO;

@Repository
public class ToDoBoardDaoImpl extends SqlSessionDaoSupport implements ToDoBoardDao{

	@Autowired
	@Override 
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		System.out.println("sql" + sqlSessionTemplate);
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	
	@Override
	public List<ToDoBoardVO> selectAllToDoBoard() {
		
		
		
		return this.getSqlSession().selectList("com.ktdsuniversity.edu.hello_spring.bbs.dao.ToDoBoardDao.selectToDoBoard");
	}


	@Override
	public int createBoard(ToDoWriteBoardVO toDoWriteBoardVO) {
		
		
		return this.getSqlSession().insert("com.ktdsuniversity.edu.hello_spring.bbs.dao.ToDoBoardDao.createBoard" , toDoWriteBoardVO);
	}


	@Override
	public int deleteBoard(int id) {
		
		return this.getSqlSession().delete("com.ktdsuniversity.edu.hello_spring.bbs.dao.ToDoBoardDao.deleteBoard" , id);
		
	}


	@Override
	public int updateBoard(int id) {
		return this.getSqlSession().update("com.ktdsuniversity.edu.hello_spring.bbs.dao.ToDoBoardDao.updateBoard" , id);
		
	}

}
