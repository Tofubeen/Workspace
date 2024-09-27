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
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;


//Spring Application Bean 을 생성해주는 Annotation
@SpringBootTest
//JUNIT5 를 사용하기 위한 설정
//@ExtendWith(SpringExtension.class)
//Test 하고자 하는 클래스와 Test에 필요한 클래스들을 임포트
//BoardServiceImpl.class : BoardServiceImpl 테스트 하기 위해 임포트 
//BoardDaoImpl.class : BoardServiceImpl 에BoardDaoImpl 를 Autowired 하기 위해 임포트 
@Import({BoardServiceImpl.class, BoardDaoImpl.class})
public class BoardServiceImplTest {

	@Autowired 
	private BoardService boardService; //임포트 한BoardServiceImpl 에 인스턴스를  boardServiceImpl에 넣어준다
	
	
	/**
	 * JUNIT5 테스트를 위해 BoardServiceImpl 에 가짜 인스턴스를 DI 시킨다
	 */
	@MockBean
	private BoardDao boardDao;// 가짜인스턴스를 넣어주는것 --> 
	
	
	@Test
	public void testInsert() {
		
		WriteBoardVO writeBoardVO = new WriteBoardVO();
		
		BDDMockito.given(this.boardDao.insertNewBoard(writeBoardVO)).willReturn(3);
		
		int result = this.boardDao.insertNewBoard(writeBoardVO);
		
		assertEquals(3, result);
		
		
	}
	
	
}
