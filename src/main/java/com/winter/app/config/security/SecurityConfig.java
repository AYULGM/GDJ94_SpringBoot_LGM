package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailHandler loginFailHandler;
	@Autowired
	private Logout logout;
	@Autowired
	private LogoutSuccess logoutSuccess;
	
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
					// 로그인 진행할 URL(위에 코드가 있어서 굳이 안적어도 잘됨, 혹시라도 문제가 생기면 적는 코드)
					.loginProcessingUrl("/users/login")
					//.usernameParameter("id")
					//.passwordParameter("pw")
					//.defaultSuccessUrl("/") // 성공했을때 리다이렉트 URL
					//.failureUrl("/")
					
					.failureHandler(loginFailHandler) // 얘도 똑같이 Autowired로 주입받음
					.successHandler(loginSuccessHandler) // Autowired로 주입받음
					
					;
				
				
				
			})
			
			.logout((logout)->{
				logout
					.logoutUrl("/users/logout")
//					.logoutSuccessUrl("/")
					.addLogoutHandler(this.logout)
					.logoutSuccessHandler(logoutSuccess)
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









