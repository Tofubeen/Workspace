package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.dao.impl.BoardDaoImpl;
import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;



@SpringBootTest
@Import({BoardServiceImpl.class, BoardDaoImpl.class})
public class BoardServiceImplTest {
	
	@Autowired
	private BoardService boardService;
	
	@MockBean
	private BoardDao boardDao;
	
	@Test
	public void getOneBoard() {
		BoardVO boardVO = new BoardVO();
		
		
		boardVO.setViewCnt(1);
		
		
		BDDMockito.given(boardDao.increaseViewCount(15)).willReturn(1);
		BDDMockito.given(boardDao.selectDetailBoard(15)).willReturn(boardVO);
		
		
		
		
		int viewCount = boardService.getDetailBoard(15,true).getViewCnt();
		BoardVO newBoard = boardService.getDetailBoard(15 , true);
		
		
		
		
		assertEquals(1, viewCount);
		assertEquals(boardVO, newBoard);
	}
	
}
