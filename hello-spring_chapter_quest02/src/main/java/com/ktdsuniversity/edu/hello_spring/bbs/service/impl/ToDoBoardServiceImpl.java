package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.ToDoBoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.service.ToDoBoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoWriteBoardVO;

@Service
public class ToDoBoardServiceImpl implements ToDoBoardService{

	
	@Autowired
	private ToDoBoardDao toDoBoardDao;

	@Override
	public ToDoBoardListVO selectAllToDoBoard() {
		
		List<ToDoBoardVO> toDoBoardVO = this.toDoBoardDao.selectAllToDoBoard();
		
		ToDoBoardListVO toDoBoardListVO = new ToDoBoardListVO();
		toDoBoardListVO.setBoardList(toDoBoardVO);
		
		return toDoBoardListVO;
	}

	@Override
	public int createBoard(ToDoWriteBoardVO toDoWriteBoardVO) {
		
		int success = this.toDoBoardDao.createBoard(toDoWriteBoardVO);
		
		return success;
	}

	@Override
	public int updateBoard(int id) {
		int success = this.toDoBoardDao.updateBoard(id);
		return success;
	}

	@Override
	public int deleteBoard(int id) {
		int success = this.toDoBoardDao.deleteBoard(id);
		return success;
	}
	
	
	

	
}
