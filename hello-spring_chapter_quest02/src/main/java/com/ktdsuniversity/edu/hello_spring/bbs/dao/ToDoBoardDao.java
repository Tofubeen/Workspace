package com.ktdsuniversity.edu.hello_spring.bbs.dao;

import java.util.List;

import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoWriteBoardVO;

public interface ToDoBoardDao {

	public List<ToDoBoardVO> selectAllToDoBoard();
	
	public int createBoard(ToDoWriteBoardVO toDoWriteBoardVO);
	
	public int deleteBoard(int id);
	
	public int updateBoard(int id);
}
