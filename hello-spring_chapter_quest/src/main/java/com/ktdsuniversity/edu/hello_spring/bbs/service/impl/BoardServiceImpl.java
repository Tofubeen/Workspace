package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	
	@Autowired
	private BoardDao boardDao;
	
	
	@Override
	public BoardListVO getAllBoard() {
		//게시글 목록 화면에 데이터를 전송해주기 위해서
		//게시글의 건수와 게시글의 목록을 조회해 반환시킨다
		
		//1. 게시글의 건수를 조회한다.
		int boardCount = this.boardDao.selectBoardAllCount(); //현재 boardDao에 저장된 게시글 총 갯수를 가지고 온다
		
		//2. 게시글의 목록을 조회한다.
		List<BoardVO> boardList = this.boardDao.selectAllBoard();// 현재 boardDao에 저장된 게시글 총 목록을 가지고 온다
		
		//3. BoardListVO을 만들어서 게시글의 건수와 목록을 할당한다 // boardListVO 를 만들어 가지고온 총 갯수와 총 목록을 삽입해준다
		BoardListVO boardListVO = new BoardListVO();
		boardListVO.setBoardCnt(boardCount);
		boardListVO.setBoardList(boardList);
		
		//4. BoardListVO 인스턴스를 반환한다 // 데이터를 넣어준 boardlistVO를 반환한다
		
		return boardListVO;
	}
	@Override
	public boolean createNewBoard(WriteBoardVO writeBoardVO) {
		int result = this.boardDao.insertNewBoard(writeBoardVO);
		
		
		return result > 0  ;
	}

	
	
	
	
	
	
	
	
	//과제부분------------------------------------------------------------------------------------
	@Override
	public BoardVO getDetailBoard(int id) {
		
		int successCnt = this.boardDao.increaseViewCount(id);
		
		if(successCnt == 0) {
			
			throw new IllegalArgumentException("게시글이 안가져와져서 조회수 안올라갔어요");
		}
		
		BoardVO detailBoard = this.boardDao.selectDetailBoard(id);
		
		
		return detailBoard;
	}

	
}
