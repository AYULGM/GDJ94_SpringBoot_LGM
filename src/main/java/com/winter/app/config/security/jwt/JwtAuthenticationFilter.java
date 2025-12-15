package com.winter.app.config.security.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		chain.doFilter(request, response);
	}
}
