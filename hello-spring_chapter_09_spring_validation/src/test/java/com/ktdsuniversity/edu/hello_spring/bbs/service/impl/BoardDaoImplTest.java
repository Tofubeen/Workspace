package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.dao.impl.BoardDaoImpl;
import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;



@MybatisTest
@AutoConfigureTestDatabase(replace = Replace.NONE) // DataSource 를 대체하지 않음
@Import(BoardDaoImpl.class)
public class BoardDaoImplTest {

	@Autowired 
	private BoardDao boardDao; 
	
	
	@Test
	public void testSelectDetailBoard() {
		int id = 3;
		BoardVO board = this.boardDao.selectDetailBoard(id);
		System.out.println("조회된 게시글 : " + board);
	}
	
	@Test
	public void testUpdateViewCount() {
		//MyBatisSystemError
		int id = 15;
		int success = this.boardDao.increaseViewCount(id);
		
		assertTrue(success == 1);
		System.out.println("조회수 : " + success);
	}

	

	
	
}
