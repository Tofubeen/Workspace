package com.ktdsuniversity.edu.hello_spring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ktdsuniversity.edu.hello_spring.service.HelloBootService;

@Controller
public class HelloBootController {

	/**
	 * Di (Dependency Injection): Spring Bean Container 에 있는 인스턴스를 주입한다
	 * 멤버변수의 타입과 Spring Bean Container 에 있는 인스턴스의 타입이 같은 것만 주입한다.
	 */
	
	
	@Autowired // 
	private HelloBootService helloBootService; 
	
	
	
	
	
	
	
	public HelloBootController() {
		System.out.println("컨트롤러가 적용된 클래스는 스프링이 직접 인스턴스로 만들어서 빈 컨트롤러에 저장된다");
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND); 
	}
	
//	@GetMapping("/print")
//	public ResponseEntity<String> hello() {
//		
//		return new ResponseEntity<String>("Hello , Boot Controller" , HttpStatus.OK); 
//	}
	@GetMapping("/print")
	public void hello2() {
		
		System.out.println("브라우저에 의해서 호출되었습니다");; 
	}
	
	
	
	
	
	@GetMapping("/")
	public ResponseEntity<String> printHtml() {
		return new ResponseEntity<String>("""
				<!DOCTYPE html>
				<html>
					<head>
						<title>Spring Test</title>
					</head>
					<body>
						<h1>Hello Spring Boot</h1>
						<p>Test Case</p>
						<button>text</button>
						</body>
					</html>
				""",HttpStatus.NOT_FOUND);
	}
	
	/**
	 * http://localhost:8080/jsp 로 브라우저가 요청을 하면
	 * 스프링 컨트롤러는 /WEB-INF/views/hellojsp.jsp 를 읽어와서
	 * html 로 변환한 후 브라우저에게 돌려준다
	 * 
	 */
	@GetMapping("/jsp") //http://localhost:8080/jsp
	public String viewJSP() {
		
		return "hellojsp";
	}
	
	@GetMapping("/jsp2") //http://localhost:8080/jsp
	public ModelAndView viewJSPWithModelAndView() {
		ModelAndView view = new ModelAndView();
		
		//Model : jsp에 보내질 데이터 
		//view : jsp 에게 보여줄 화면?
		view.setViewName("hellojsp");
		view.addObject("applicationName", "하이");
		
		return view;
	}
	
	@GetMapping("/jsp3") //http://localhost:8080/jsp
	public String viewJSPWithModel(Model model) {
		
		String greetingMessage =  this.helloBootService.getGreetingMessage();
		
		model.addAttribute("applicationName", greetingMessage);

		
		
		
		return "hellojsp";
	}
	
	
	
	
}
