package com.ktdsuniversity.edu.hello_spring.bbs.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.hello_spring.bbs.service.ToDoBoardService;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardListVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoBoardVO;
import com.ktdsuniversity.edu.hello_spring.bbs.vo.ToDoWriteBoardVO;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class ToDoController {

	
	@Autowired
	private ToDoBoardService toDoBoardService;
	
	@GetMapping("/board/todo")
	public String viewToDoBoard(Model model) {
		
		ToDoBoardListVO toDoBoardListVO = this.toDoBoardService.selectAllToDoBoard();
		
		
		
		model.addAttribute("toDoBoardListVO" , toDoBoardListVO);
		
		return "board/todoboard";
	}
	
	@GetMapping("/board/remove/{id}")
	public String deleteBoard(@PathVariable int id) {
		int success = this.toDoBoardService.deleteBoard(id);
		System.out.println(success + " 가 성공 되었습니다.");
		
		return "redirect:/board/todo";
	}
	
	@GetMapping("/board/write")
	public String viewCreateBoard() {
		
		
		
		
		return "board/todowrite";
	}
	
	@PostMapping("board/write")
	public String createBoard(ToDoWriteBoardVO toDoWriteBoardVO ) {
		System.out.println(toDoWriteBoardVO.getSubject() + " " + toDoWriteBoardVO.getEndDt());
		
		int success = this.toDoBoardService.createBoard(toDoWriteBoardVO);
		System.out.println(success + "건이 삽입되었습니다");
		return "redirect:todo";
	}
	
	@GetMapping("/board/success/{id}")
	public String getMethodName(@PathVariable int id) {
		
		int success = this.toDoBoardService.updateBoard(id);
		
		return "redirect:/board/todo";
	}
	
	
	

	
	
	
}
