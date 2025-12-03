package com.winter.app.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserFilter implements Filter{
	
	//init , destroy는 딱히 필요없다고 말은 하심
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest req = (HttpServletRequest)request;
		Object obj = req.getSession().getAttribute("user");
		
		if(obj == null) {
			((HttpServletResponse)response).sendRedirect("/users/login");
			return;
		}
		
		// 다음 필터나 필터가 없으면 서블릿으로 전송
		chain.doFilter(request, response);
	}
}
