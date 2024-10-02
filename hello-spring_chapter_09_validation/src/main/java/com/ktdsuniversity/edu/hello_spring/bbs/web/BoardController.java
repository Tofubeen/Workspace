package com.ktdsuniversity.edu.hello_spring.bbs.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;


import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ModifyBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;
import com.ktdsuniversity.edu.hello_spring.common.beans.FileHandler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class BoardController {

	//BoardServiceImpl에 구현된 인스턴스를 boardService에 주입( 스프링 이 생성시켜준다 )
	//BoardService은 인터페이스기에 BoardServiceImpl 에 생성되있는 인스턴스를 가져온다
	
	@Autowired
	private FileHandler fileHandler;
	
	
	@Autowired
	private BoardService boardService; 
	
	
	@GetMapping("/board/list") //http://localhost:8080/board/list --> 브라우저에서 /board/list 로 접속시 리턴에 있는 경로에 jsp를 출력한다
	public String viewBoardList(Model model) {
		
		BoardListVO boardListVO = this.boardService.getAllBoard();
		
		model.addAttribute("boardListVO" , boardListVO); //return 하면 model도 같이 전달된다
		
		
		return "board/boardlist"; //이 경로에 앞에는 prefix 와 suffix에 있는 경로가 생략되어있다
	}
	
	@GetMapping("/board/write")
	public String viewBoardWritePage() {
		
		return "board/boardwrite";
	}
//	
//	
//	@PostMapping("board/write")
//	public String doSomething() {
//		
//		
//		
//		return "board/boardwrite";
//		
//	}
	
	@PostMapping("board/write")
	public String createNewBoard(WriteBoardVO writeBoardVO , Model model) {
		
		if(writeBoardVO.getSubject() == null || writeBoardVO.getSubject().length() == 0){
			
			model.addAttribute("error_subject" , "제목은 필수 입력값입니다.");
			model.addAttribute("writeBoardVO" , writeBoardVO);
			return "board/boardwrite";
		}
		
		if(writeBoardVO.getSubject() != null && writeBoardVO.getSubject().length() < 5){
			
			model.addAttribute("error_subject" , "제목은 다섯글자 이상 적어주세요.");
			model.addAttribute("writeBoardVO" , writeBoardVO);
			return "board/boardwrite";
		}
		
		
		
		
		if(writeBoardVO.getEmail() == null || writeBoardVO.getEmail().length() == 0){
			
			model.addAttribute("error_subject" , "이메일은 필수 입력값입니다.");
			model.addAttribute("writeBoardVO" , writeBoardVO);
			return "board/boardwrite";
		}
		
		boolean result =  this.boardService.createNewBoard(writeBoardVO);
		System.out.println("등록 결과 : " + result);
		return "redirect:/board/list";
	}
	
	@GetMapping("/board/detail")
	public String viewDetailBoard(Model model , @RequestParam int id) {
		
		BoardVO board =  this.boardService.getDetailBoard(id , true);
		model.addAttribute(board);
		
		return "board/boardview";
	}
	
	
	@GetMapping("/board/modify/{id}")
	public String viewBoardModifyPage(@PathVariable int id , Model model) {
		//@PathVariable 은 url 경로에 {id}을 가져온다
		BoardVO boardVO = this.boardService.getDetailBoard(id , false);
		
		model.addAttribute("boardVO" , boardVO);
		
//		return "redirect:/board/detail?id={id}";
		return "board/boardmodify";
	}
	
	@PostMapping("board/modify/{id}")
	public String doModifyOneBoard(@PathVariable int id , ModifyBoardVO modifyBoardVO , Model model) {
		// TODO set ID
		modifyBoardVO.setId(id);
		
		boolean isUpdated = this.boardService.updateOneBoard(modifyBoardVO);
		
		//TODO post Update process
		if(isUpdated) {
			//성공적으로 수정했다면 , 수정한 게시글의 상세조회 페이지(id값을 포함한 경로)로 이동시킨다
			return"redirect:/board/detail?id=" + id;
		}else {
			//TODO 사용자가 작성했던 내용을 JSP에 그대로 보내준다
			model.addAttribute("boardVO" , modifyBoardVO);
			return "board/boardmodify";
		}
		
		
	}
	
	
	@GetMapping("/board/delete/{id}")
	public String doDeleteOneBoard(@PathVariable int id ) {
		boolean isDelete = this.boardService.deleteOneBoard(id);
		
		
		if(isDelete) {

			return"redirect:/board/list";
		}else {

			return "redirect:/board/detail?id=" + id;
		}
	}
	
	
	@GetMapping("/board/file/download/{id}")
	public ResponseEntity<Resource> doDownload(@PathVariable int id) {
		
		//1. 다운로드 할 파일의 이름을 알기 위해 게시글을 조회한다
		BoardVO boardVO = this.boardService.getDetailBoard(id, false);
		
		return this.fileHandler.downloadFile(boardVO.getFileName(), boardVO.getOriginFileName());
	}
	
	
	

	
	
}
