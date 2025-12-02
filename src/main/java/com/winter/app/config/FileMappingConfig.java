package com.winter.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.files.FileManager;

// @configuration을 달면 스프링이 찾아서 본다.
@Configuration
public class FileMappingConfig implements WebMvcConfigurer{

	@Value("${app.upload}")
	private String uploadPath; //  file:///lgm/upload/
	
	@Value("${app.upload.url}")
	private String urlPath; //  /files/**

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry // 메서드 체이닝 이용
		.addResourceHandler(urlPath) // 이런 url이 들어오면
		.addResourceLocations(uploadPath); // uploadPath로 가라.  Location"s"라 ,(컴마)로 여러개 지정가능
	}
	
	
	
	// @어노테이션으로 못부르는 경우 이런식으로 만든다.
	// 파일 매니저 객체를 만들겠다.(API로 받은 클래스라는 설정 하)
	// public에 밑줄그어져있어서 default(생략가능)로 씀 , Bean에 이름따로 안주면 첫글자를 소문자로하는...
	
//	@Bean("") 
//	FileManager getFileManager() {
//		return new FileManager();
//	}
}
