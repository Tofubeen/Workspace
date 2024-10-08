package com.ktdsuniversity.edu.hello_spring.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.hello_spring.bbs.dao.BoardDao;
import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;
import com.ktdsuniversity.edu.hello_spring.common.beans.FileHandler;
import com.ktdsuniversity.edu.hello_spring.common.vo.StoreResultVO;

@Service
public class BoardServiceImpl implements BoardService{

	//application.yml 파일에서 "app.multipart.base-dir" 설정 값을 가져온다
	//@Value 는 Spring Been에서만 사용이 가능
	//Spring Been :@Controller , @Service , @Repository
	
//	@Value("${app.multipart.base-dir}")
//	private String baseDirectory;
	
	@Autowired
	private FileHandler fileHandler;
	
	
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
		
		// 파일 업로드 처리
		MultipartFile file = writeBoardVO.getFile(); 
		
		StoreResultVO storeResultVO = this.fileHandler.storeFile(file);
		if(storeResultVO != null) {
			writeBoardVO.setFileName(storeResultVO.getObfuscatedFileName());
			writeBoardVO.setOriginFileName(storeResultVO.getOriginFileName());
		}
		
		
		
		int result = this.boardDao.insertNewBoard(writeBoardVO);
		
		
		return result > 0  ;
	}

	
	
	
	
	
	
	
	
	//과제부분------------------------------------------------------------------------------------
	@Override
	public BoardVO getDetailBoard(int id , boolean isIncrease) {
		
		if(isIncrease) {
		int successCnt = this.boardDao.increaseViewCount(id);
		
		if(successCnt == 0) {
			
			throw new IllegalArgumentException("게시글이 안가져와져서 조회수 안올라갔어요");
			}
		}
		
		BoardVO detailBoard = this.boardDao.selectDetailBoard(id);
		if(detailBoard == null) {
			throw new IllegalArgumentException("잘못된 접근");
		}
		
		
		
		return detailBoard;
	}
	@Override
	public boolean updateOneBoard(ModifyBoardVO modifyBoardVO) {
		
		//기존의 파일을 삭제하기 위해서 업데이트하기 전 게시글의 정보를 조회한다
		BoardVO boardVO = this.boardDao.selectDetailBoard(modifyBoardVO.getId());
		
		MultipartFile file = modifyBoardVO.getFile();
		
		StoreResultVO storeResultVO = this.fileHandler.storeFile(file);
		
		if(storeResultVO != null) {
			modifyBoardVO.setFileName(storeResultVO.getObfuscatedFileName());
			modifyBoardVO.setOriginFileName(storeResultVO.getOriginFileName());;
			
		}
		
		
		int successCnt = this.boardDao.updateOneBoard(modifyBoardVO);
		
		if(successCnt > 0) {
			this.fileHandler.deleteFile(boardVO.getFileName());
		}
		
		
		return successCnt > 0;
	}
	
	
	@Override
	public boolean deleteOneBoard(int id) {
		
		BoardVO boardVO = this.boardDao.selectDetailBoard(id);
		int successCnt = this.boardDao.deleteOneBoard(id);
		
		if(successCnt > 0) {
			this.fileHandler.deleteFile(boardVO.getFileName());
		}
		
		return successCnt >0;
	}

	
}
