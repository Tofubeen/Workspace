package com.ktdsuniversity.edu.hello_spring.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.RegistMemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	

	@GetMapping("/member/regist")
	public String viewRegistMemberPage() {
		int check =  this.memberService.checkEmailCount();
		System.out.println("지금 아이디 갯수는 " + check);
		
		
		return"member/memberregist";
	}
	
	@PostMapping("/member/regist")
	public String postRegistMemberPage(@Valid RegistMemberVO registMemberVO
											, BindingResult bindingResult
											, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("registMemberVO" , registMemberVO);
			return "member/memberregist";
		}
		
		boolean success = this.memberService.doCreateNewMember(registMemberVO);
		System.out.println("회원가입 결과: " + success);
		
		return "redirect:/board/todo";
	}
	
	
	
	
	@GetMapping("/member/login")
	public String viewLoginPage() {
		
		
		return "member/memberlogin";
	}
	
	@PostMapping("/member/login")
	public String doLogin(@Valid LoginMemberVO loginMemberVO
						 ,BindingResult bindingResult
						 ,HttpSession session
						 ,Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginMemberVO" ,  loginMemberVO);
			return "member/memberlogin";
		}
		
		try {
			
			MemberVO memberVO = this.memberService.readMemberVO(loginMemberVO);
			
		} catch (IllegalArgumentException e) {
			model.addAttribute("loginMemberVO" , loginMemberVO);
			model.addAttribute("message" , e.getMessage());
			return "member/memberlogin";
		}
		
		
		
		
		return "redirect:/board/todo";
	}
	
	


//	@ResponseBody
//	@GetMapping("/member/regist/available")
//	public Map<String, Object> doCheckAvailableEmail(@RequestParam String email){
//		
//		
//		return null;
//	}
}
