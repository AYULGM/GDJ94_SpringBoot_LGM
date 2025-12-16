package com.winter.app.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	// 토큰 만들어주는 애
	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(JwtTokenManager manager, AuthenticationManager authenticationManager) {
		super(authenticationManager);
		this.jwtTokenManager = manager;
	}
	
	// Token 검증
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Cookie [] cookies = request.getCookies();
		String token="";
		if(cookies != null) {
			for(Cookie c: cookies) {
				if(c.getName().equals("access-token")) {
					token = c.getValue();
					break;
				}
			}
		}
	
		if(token != null && token.length() > 0) {
			try {
				Authentication authentication = this.jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication); // 서버 내부에서는 임시로 세션을 이용하겠다.
			} catch (Exception e) {
				// SecurityException || MalformedException || SignatureException : 3개 타입은 공통적으로 유효하지 않는 JWT 서명이라는 뜻(서명이 잘못되었다.)
				// ExpiredJwtException : 유효시간이 만료된 Token (이 예외 빼고 나머지는 우리가 만든 토큰이 아닌 다른사람이 만든 토큰일 수 있고 훼손된 토큰일 수 있다.)
				// UnSupportedJwtException : 지원되지 않는 Token
				// IllegalArgumentException : 잘못된 Token
				
				System.out.println(e.getMessage()); // 토큰을 검증했을때 exception이 발생하면 메세지를 찍어달라.
				
				if(e instanceof ExpiredJwtException) {
					// RefreshToken으로 AccessToken 생성
					// DB에서 조회 또는 저장소에서 가져오기
					String refresh="";
					for(Cookie c: cookies) {
						if(c.getName().equals("refresh-token")) {
							refresh = c.getValue();
							break;
						}
					}
					
					// refresh token을 검증(잘못된 토큰이 오거나 만료된 토큰이 올 수 도있기때문)
					try { // authentication에 사용자의 정보나 권한 등을 가져옴 , 토큰이 유효하다면 사용자 정보를 가지고 온다.
						Authentication authentication = jwtTokenManager.getAuthenticationByToken(refresh);
						SecurityContextHolder.getContext().setAuthentication(authentication);
						// Access token 생성(쿠키에 담아서 클라이언트에 보내줘야함)
						String newtoken = jwtTokenManager.makeAccessToken(authentication);
						Cookie c = new Cookie("access-token", newtoken);
						c.setPath("/"); // 어느 URL이 왔을때만 쿠키를 보낼거야?
						c.setMaxAge(60); // 1분
						c.setHttpOnly(true); // http 상태에서만 받겠다는 뜻
						
						response.addCookie(c); // 새로운 쿠키에 access-token을 담아 응답으로 보내자.
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					
				}
			}
		}
	
		chain.doFilter(request, response);
	}
}
