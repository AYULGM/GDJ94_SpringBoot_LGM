package com.winter.app.config.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
//		log.info("{}", exception.getMessage());
//		log.info("{}", exception.getClass());
//		log.info("{}", exception.getClass().toString());
		String message = "로그인 실패 ㅠㅠ";
		if(exception instanceof AccountExpiredException) {
			message = "계정 유효기간 만료";
		}
		if(exception instanceof LockedException) {
			message = "계정 잠김";
		}
		if(exception instanceof CredentialsExpiredException) {
			message = "비밀번호 유효기간 만료";
		}
		if(exception instanceof DisabledException) {
			message = "휴면 계정";
		}
		if(exception instanceof BadCredentialsException) {
			message = "비밀번호 틀림";
		}
		if(exception instanceof InternalAuthenticationServiceException) {
			message = "아이디 틀림";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?message=" + message);
		
	}
	
}
