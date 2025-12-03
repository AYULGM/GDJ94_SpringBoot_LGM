package com.winter.app.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.filters.UserFilter;

import jakarta.servlet.Filter;

//@Configuration //주석처리하면 효력을 잃음
public class UserFilterMappingConfig implements WebMvcConfigurer{ //굳이 필터를 하나 만든이유가 구분하기 쉬우라고 클래스 하나 더만든거임

	@Bean
	FilterRegistrationBean<Filter> filterRegistrationBean() {
		FilterRegistrationBean<Filter> f = new FilterRegistrationBean<>();
		f.setFilter(new UserFilter());
		f.addUrlPatterns("/qna/*", "/notice/add"); // 가변인자(...)를 받고 s(복수형), 이 URL을 들어가려하면 로그인이 필요함
		
		return f;
	}
	
}
