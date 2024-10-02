package com.ktdsuniversity.edu.hello_spring.bbs.dao;

import java.util.List;

import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;



public interface BoardDao {


	public int selectBoardAllCount();
	public List<BoardVO> selectAllBoard();
	public int insertNewBoard(WriteBoardVO writeBoardVO);
	

	
	
	
	//과제부분--------------------------------------------------------------------------------------------
	
	public BoardVO selectDetailBoard(int id);
	
	public int increaseViewCount(int id);
	
	public int updateOneBoard(ModifyBoardVO modifyBoardVO);
	
	public int deleteOneBoard(int id);
	
}
