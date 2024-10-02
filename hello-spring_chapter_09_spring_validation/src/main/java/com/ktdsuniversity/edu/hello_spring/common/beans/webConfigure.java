package com.ktdsuniversity.edu.hello_spring.common.beans;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//-Spring Validator
//-Spring InteCeptor
//- ....
@Configuration //application.yml 에서 설정하지 못하는 디테일한 설정을 위한 애노테이션
@EnableWebMvc // spring WebMVC에 필요한 다양한 요소를 활성화 시키는 애노테이션.
public class webConfigure implements WebMvcConfigurer{
//@configuration 의 설정이 application 의 설정보다 우선순위를 가짐
//그래서 yml의 설정을 몇개 가져와야 함	
	
	
	
	/**
	 * JSP view Resolver 설정
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/" , ".jsp");
	}
	
	
	/**
	 * Static Resource 설정(css 나 js 적용을 가능하게 하기위한 설정)
	 */
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			
			registry.addResourceHandler("/css/**") //http://localhost:8080/css/common.css
					.addResourceLocations("classpath:/static/css/");
			registry.addResourceHandler("/js/**") //http://localhost:8080/js/jquery/jquery-3.1.7.min.js
					.addResourceLocations("classpath:/static/js/");
		}
	
	
	
}