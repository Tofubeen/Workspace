package com.ktdsuniversity.edu.hello_spring.bbs.service;

import java.util.List;

import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoWriteBoardVO;

public interface ToDoBoardService {

	public ToDoBoardListVO selectAllToDoBoard();
	
	public int createBoard(ToDoWriteBoardVO toDoWriteBoardVO);
	
	public int updateBoard(int id);
	
	public int deleteBoard(int id);
	
}
