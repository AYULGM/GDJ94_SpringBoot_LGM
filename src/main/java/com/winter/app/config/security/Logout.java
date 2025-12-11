package com.winter.app.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.users.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

@Component
public class Logout implements LogoutHandler{
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		if(authentication == null) {
			return;
		}
		System.out.println("logout handler");
		// kakao 서버로 로그아웃 요청을 보내자.
		
		// 공통적인 요소를 추가하고싶으면 builder
		// 단순하게 webClient객체를 만들고 싶으면 create
		
		
	// 1) 이건 서비스에서만 로그아웃하는거 , 세션이 계속 남음
//		WebClient webClient = WebClient.create();
//		
//		Mono<String> result = webClient
//		.post()
//		.uri("https://kapi.kakao.com/v1/user/logout")
//		.header("Authorization", "KakaoAK " +adminKey) // KakaoAK하고 스페이스바 하나 넣어줘야하는거 주의 (문서 > 카카오 로그인 > REST API에서 로그아웃 목차)
//		.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
//		.body(BodyInserters.fromFormData("target_id_type", "user_id").with("target_id", authentication.getName()))
//		.retrieve()
//		.bodyToMono(String.class)
//		;
//		System.out.println(result.block());
		
	// 2) 카카오 계정과 함께 로그아웃
		UserDTO userDTO = (UserDTO)authentication.getPrincipal();
		// 일반회원이 로그인하는거냐 아니냐로 분기처리
		if(userDTO.getSns() != null) { // 소셜로그인
			try {
				response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+restKey+"&logout_redirect_uri=http://localhost/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else { //소셜로그인이 아니라면
			try {
				response.sendRedirect("/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().invalidate(); //세션 만료시킴
		
		
	}
}
