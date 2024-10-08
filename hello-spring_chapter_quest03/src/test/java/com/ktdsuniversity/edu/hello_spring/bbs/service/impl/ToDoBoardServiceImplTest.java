package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.impl.ToDoBoardDaoImpl;



@SpringBootTest
@Import({ToDoBoardServiceImpl.class, ToDoBoardDaoImpl.class})
public class ToDoBoardServiceImplTest {
	
//	@Autowired
//	private ToDoBoardService boardService;
//	
//	@MockBean
//	private BoardDao boardDao;
//	
//	@Test
//	public void getOneBoard() {
//		BoardVO boardVO = new BoardVO();
//		
//		
//		boardVO.setViewCnt(1);
//		
//		
//		BDDMockito.given(boardDao.increaseViewCount(15)).willReturn(1);
//		BDDMockito.given(boardDao.selectDetailBoard(15)).willReturn(boardVO);
//		
//		
//		
//		
//		int viewCount = boardService.getDetailBoard(15,true).getViewCnt();
//		BoardVO newBoard = boardService.getDetailBoard(15 , true);
//		
//		
//		
//		
//		assertEquals(1, viewCount);
//		assertEquals(boardVO, newBoard);
//	}
	
}
