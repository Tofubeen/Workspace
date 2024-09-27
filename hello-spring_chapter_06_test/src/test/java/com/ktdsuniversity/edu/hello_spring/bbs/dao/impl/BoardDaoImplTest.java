package com.ktdsuniversity.edu.hello_spring.bbs.dao.impl;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;

@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(BoardDaoImpl.class)
public class BoardDaoImplTest {

	@Autowired
	private BoardDao boardDao; // 데이터 베이스에 연결하기 위해 BoardDao 를 autowired를 해준다
	

	
	@Test
	public void testInsertNewBoard() {
		WriteBoardVO writeBoardVO = new WriteBoardVO(); // 갯수
		
		writeBoardVO.setSubject("james");
		writeBoardVO.setEmail("rnjsgur1215@hanmail.net");
		writeBoardVO.setContent("내용");
		
		int result = this.boardDao.insertNewBoard(writeBoardVO);
		assertTrue(result == 2);
		System.out.println("성공갯수 : " + result);
		
		
	}
}
