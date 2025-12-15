package com.winter.app.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	// 어노테이션으로 줘도되긴하는데 나중에 꼬이기때문에 this.tokenManager로 대체함
	private JwtTokenManager tokenManager;
	
	public JwtLoginFilter(JwtTokenManager tokenManager, AuthenticationManager authenticationManager) {
		this.setFilterProcessesUrl("/users/loginProcess");
		this.tokenManager = tokenManager;
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("login 시도");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// UserDetailService의 loadUserByusername()을 호출하고
		// 패스워드가 일치하는지 판별하고 Authentication 객체를 
		// SecurityContextHolder에 넣어줌.
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String accesstoken = tokenManager.makeAccessToken(authResult);
		String refreshToken = tokenManager.makeRefreshToken(authResult); // 보통 서버에 저장(refreshToken이 만료되면 보통 블랙리스트에 저장, 또는 DB에서 삭제)
		
		Cookie cookie = new Cookie("access-token", accesstoken);
		cookie.setPath("/"); // 어느 URL이 왔을때만 쿠키를 보낼거야?
		cookie.setMaxAge(60); // 1분
		cookie.setHttpOnly(true); // http 상태에서만 받겠다는 뜻
		
		response.addCookie(cookie);
		
		cookie = new Cookie("refresh-token", refreshToken); // 원래 리프레시 토큰은 잘 안내보냄.
		cookie.setPath("/"); // 어느 URL이 왔을때만 쿠키를 보낼거야?
		cookie.setMaxAge(60); // 1분
		cookie.setHttpOnly(true); // http 상태에서만 받겠다는 뜻
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		super.unsuccessfulAuthentication(request, response, failed);
	}

}
