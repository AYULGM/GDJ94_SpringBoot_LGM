package com.winter.app.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//정적자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		
		return web -> {
			web
				.ignoring()
					.requestMatchers("/css/**")
					.requestMatchers("/images/**", "/img/**")
					.requestMatchers("/js/**", "/vendor/**")
					;
		};
		
	} 
	
	//인증과 인가에 관한 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		
		security
			.cors((cors)->{cors.disable();})
			.csrf((csrf)->{csrf.disable();})
			
			//인가(권한)에 관한 설정
			.authorizeHttpRequests((auth)->{
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
					.requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("MANAGER", "ADMIN")
					.requestMatchers("/product/**").authenticated()
					.requestMatchers("/user/mypage", "/user/update", "/user/logout").authenticated()
					.anyRequest().permitAll()
					;
			})
			
			//Login form과 그외 관련 설정
			.formLogin((form)->{
				form
					//로그인폼 jsp 경로로 가는 url과 로그인 처리 url 작성
					.loginPage("/users/login")
					//.usernameParameter("id")
					//.passwordParameter("pw")
					.defaultSuccessUrl("/")
					//.failureUrl("/")
					;
				
				
				
			})
			
			.logout((logout)->{
				logout
					.logoutUrl("/users/logout")
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					;
			})
			
			
			;
	
		return security.build();
	}
	
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}









