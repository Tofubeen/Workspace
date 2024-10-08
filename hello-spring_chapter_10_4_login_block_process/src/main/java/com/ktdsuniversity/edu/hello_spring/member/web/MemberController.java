package com.ktdsuniversity.edu.hello_spring.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.LoginMemberVO;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	

	@GetMapping("/member/regist")
	public String viewRegistMemberPage() {
		
		return"member/memberregist";
	}
	
	@PostMapping("member/regist")
	public String postRegistMemberPage(@Valid MemberVO memberVO
											, BindingResult bindingResult
											, Model model) {
		
		System.out.println("이름은" + memberVO.getName());
		int people = this.memberService.checkEmailCount();
		System.out.println("사람수는 " + people);
		
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("memberVO", memberVO);
			return "member/memberregist";
		}
		
		if(!(memberVO.getPassword().equals(memberVO.getConfirmPassword())) ) {
			model.addAttribute("error" , "비밀번호가 일치하지 않습니다");
			model.addAttribute("memberVO" , memberVO);
			
			return"member/memberregist";
		}
		
		
		
		boolean success = this.memberService.doCreateNewMember(memberVO);
		System.out.println("회원가입 결과 : " + success);
		
		
		return "redirect:/member/login";
	}
	
	
	@ResponseBody
	@GetMapping("/member/regist/available")
	public Map<String, Object> doCheckAvailableEmail(@RequestParam String email){
		
		boolean isAvailable = this.memberService.checkAvailableEmail(email);
		System.out.println(isAvailable);
		
		Map<String, Object> response = new HashMap<>();
		response.put("email", email);
		response.put("isAvailable", isAvailable);
		
		return response;
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
			
			model.addAttribute("loginMemberVO" , loginMemberVO); // BindingResult에 에러가 생기면 그 에러를 memberlogin 에 전송(사실 에러는 jsp에 존재해있음)
			
			return "member/memberlogin";
		}
		
		try {
			MemberVO memberVO = this.memberService.readMemberVO(loginMemberVO);
			
			//로그인 상태를 서버에 저장시킨다.
			session.setAttribute("_LOGIN_USER", memberVO);
						
		} catch (IllegalArgumentException e) {
			model.addAttribute("loginMemberVO" , loginMemberVO);
			model.addAttribute("message" , e.getMessage());
			return "member/memberlogin";
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/member/delete-me")
	public String doDeleteMe(@SessionAttribute(value = "_LOGIN_USER",required = false) MemberVO loginMemberVO
							, HttpSession session) {
		boolean sucess = this.memberService.deleteMe(loginMemberVO.getEmail());
		
		if(!sucess) {
			System.out.println("탈퇴실패");
			return "redirect:/member/fail-delete-me";
		}
		
		session.invalidate();
		System.out.println("탈퇴성공");
		
		return "redirect:/member/success-delete-me";
	}
	
	@GetMapping("/member/{result}-delete-me")
	public String viewDeleteMePage(@PathVariable String result) {
		result = result.toLowerCase();
		
		if(!result.equals("fail") && !result.equals("success")) {
			return "error/404";
		}
		
		return "/member/" + result + "deleteme";
	}


}
