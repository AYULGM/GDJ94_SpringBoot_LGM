package com.winter.app.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외를 전역 처리하는 Controller에 주는 어노테이션
@ControllerAdvice
public class ErrorController {

	//예외 처리 전문 메서드
	@ExceptionHandler(value = NullPointerException.class) //value를 생략해도되고 써도되고 , value대신 Exception이라고 써도될듯?
	public String exc1(Model model) {
		
		return "error/error_page";
	}
	
	// 위에서 안잡히는 예외들은 부모형인 Exception이 잡아라.
	@ExceptionHandler(Exception.class)
	public String exc2(Model model) {
		
		return "error/error_page";
	}
	
	// Exception의 부모형인 Throwable
	@ExceptionHandler(Throwable.class)
	public String exc3(Model model) {
		
		return "error/error_page";
	}
	
}
