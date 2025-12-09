package com.winter.app.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class Logout implements LogoutHandler{
@Override
public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	// TODO Auto-generated method stub
	System.out.println("logout handler");
}
}
