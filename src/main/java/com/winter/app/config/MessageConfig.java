package com.winter.app.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

	@Bean
	LocaleResolver localeResolver() {
		// 둘중에 하나 선택
		
		//1. Session에 담아서 정보 저장하는 방식
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
//		return resolver;
		
		//2. Cookie에 담아서 정보 저장하는 방식
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.KOREAN);
		
		return localeResolver;
	}
	
	// Bean으로 만들필요없음
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		return changeInterceptor;
	}
	
	// 위 코드를 Bean으로 만들고 Autowired으로 쓰려하면 객체가 두번 만들어져서 낭비임 아무튼낭비임 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.changeInterceptor()).addPathPatterns("/**");
	}
}
