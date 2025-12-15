package com.winter.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.config.security.jwt.JwtAuthenticationFilter;
import com.winter.app.config.security.jwt.JwtLoginFilter;
import com.winter.app.config.security.jwt.JwtTokenManager;
import com.winter.app.users.UserDetailServiceImpl;

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
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	// ----------------- JWT 추가 --------------------
	
	 // 나중에 얘를 필요로 하는애가 생기기떄문에 얘는 어노테이션걸었음
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
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
				// front가 분리되었을 때(25.12.15)
				form.disable(); // 폼을 비활성화 하겠다. 로그인페이지가 필요없기 때문에. 여기서는 데이터만 제공하겠다.
			})
				
			
			.logout((logout)->{
				logout
					.logoutUrl("/users/logout")
//					.logoutSuccessUrl("/")
					.addLogoutHandler(this.logout)
//					.logoutSuccessHandler(logoutSuccess) // 이걸 주석처리해야 로그아웃창이 잘뜨네.
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID")
					.deleteCookies("remember-me") // 리멤버미 쿠키의 이름
					.deleteCookies("access-token", "refresh-token") //추가 (25.12.15)
					;
			})
			
			.sessionManagement(session -> {
				session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 유지 방식을 쓰지않겠다.
						;
			})
			
			.httpBasic((h)-> {
				h.disable(); // http 기본은 쓰지않겠다.
			})
			
			.addFilter(new JwtAuthenticationFilter(jwtTokenManager, authenticationConfiguration.getAuthenticationManager())) // 로그인되어있나? 순서 중요
			.addFilter(new JwtLoginFilter(jwtTokenManager, authenticationConfiguration.getAuthenticationManager())) // 로그인 시도 시
			
			.oauth2Login(t -> {
				t.userInfoEndpoint((s)->{
					s.userService(userDetailServiceImpl);
				});
			})
			
			;
	
		return security.build();
	}
	
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}









