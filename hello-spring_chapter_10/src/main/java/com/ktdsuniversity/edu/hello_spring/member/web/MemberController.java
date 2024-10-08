package com.ktdsuniversity.edu.hello_spring.member.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import com.ktdsuniversity.edu.hello_spring.member.service.MemberService;
import com.ktdsuniversity.edu.hello_spring.member.vo.MemberVO;

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
		
		
		return "member/memberregist";
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
	
	



}
