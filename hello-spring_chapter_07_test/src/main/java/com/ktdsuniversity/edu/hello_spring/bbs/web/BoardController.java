package com.ktdsuniversity.edu.hello_spring.bbs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.hello_spring.bbs.service.BoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.BoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.WriteBoardVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class BoardController {

	//BoardServiceImpl에 구현된 인스턴스를 boardService에 주입( 스프링 이 생성시켜준다 )
	//BoardService은 인터페이스기에 BoardServiceImpl 에 생성되있는 인스턴스를 가져온다
	
	
	
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
	public String createNewBoard() {
		WriteBoardVO writeBoardVO = new WriteBoardVO();
		
		boolean result =  this.boardService.createNewBoard(writeBoardVO);
		System.out.println("등록 결과 : " + result);
		return "redirect:/board/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
