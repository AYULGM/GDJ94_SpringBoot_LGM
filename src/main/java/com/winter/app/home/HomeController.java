package com.winter.app.home;

import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.users.UserDTO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	// 로그인한 정보를 JAVA단에서 쓰고 싶을때 총 5가지 방법이 있다. 아무거나 쓰길.(index4는 에러떴었긴함)
	
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	public String index5(@AuthenticationPrincipal UserDTO userDTO) throws Exception{ // Principal 자체를 꺼내는 방법도 있다.
		//Spring boot 3.2 이상 부터 가능
		
		System.out.println(userDTO.getUsername());
		
		return "index";
	}
	
	public String index4(Principal principal) throws Exception{ // Principal 자체를 꺼내는 방법도 있다.
		// 보류, error 발생함
		UserDTO userDTO = (UserDTO)principal; //UserDetails로 해도 error뜨네
		System.out.println(userDTO.getUsername());
		
		return "index";
	}
	
	public String index3(Authentication authentication) throws Exception{
		UserDTO userDTO = (UserDTO)authentication.getPrincipal();
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
		
	}
	
	
	public String index2()throws Exception{
		Object obj =  SecurityContextHolder.getContext().getAuthentication();
		
		Authentication authentication = (Authentication)obj;
		
		UserDTO userDTO = (UserDTO)authentication.getPrincipal(); //Principal이 사용자 정보(꺼내려면 authentication이 필요)
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
	}
	
	public String index(HttpSession session) throws Exception {
		// 1.
//		Enumeration<String> en = session.getAttributeNames();
//		
//		while (en.hasMoreElements()) {
//			String k = en.nextElement();
//			System.out.println(k);
//		}
//		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		SecurityContextImpl obj = (SecurityContextImpl)session.getAttribute("SPRING_SECURITY_CONTEXT");
//		System.out.println(obj.getClass()); // 이걸로 SPRING_SECURITY_CONTEXT의 클래스명을 알아냄
		Authentication authentication = obj.getAuthentication(); //인증
		authentication.getAuthorities();
		log.info("{}", authentication);
		UserDTO userDTO = (UserDTO) authentication.getPrincipal();
		System.out.println(userDTO.getUsername());
		System.out.println(authentication.getName());
		
		return "index";
	}
}
