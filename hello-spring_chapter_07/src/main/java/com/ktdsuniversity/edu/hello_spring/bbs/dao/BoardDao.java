package com.ktdsuniversity.edu.hello_spring.bbs.dao;

import java.util.List;

import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;



public interface BoardDao {

	/**
	 * DB에 저장된 모든 게시글의 수를 조화
	 * @return
	 */
	public int selectBoardAllCount();
	
	
	/**
	 * DB 에 저장된 모든 게시글의 목록을 조회
	 * @return
	 */
	public List<BoardVO> selectAllBoard();
	
	
	public int insertNewBoard(WriteBoardVO writeBoardVO);
	
}
