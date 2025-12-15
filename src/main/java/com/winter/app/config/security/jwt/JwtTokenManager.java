package com.winter.app.config.security.jwt;

import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.winter.app.users.UserDAO;
import com.winter.app.users.UserDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	
	@Value("${jwt.accessValidTime}")
	private Long accessValidTime;
	
	@Value("${jwt.refreshValidTime}")
	private Long refreshValidTime;
	
	@Value("${jwt.issuer}")
	private String issuer;

	@Value("${jwt.secretKey}")
	private String secret; // 이거 노출되면 다시 디코딩되기때문에 노출되면안됨
	
	private SecretKey key;
	
	@Autowired
	private UserDAO userDAO;
	
	@PostConstruct //생성자가 만들어지고 난 후
	public void init() {
//		String k = Base64.getEncoder().encodeToString(secret.getBytes()); // 일종의 salt(소금)역할 , 암호화할때 복호화 쉽게 못하게끔 , 물론 이거 자체로도 써도되긴함
//		key = Keys.hmacShaKeyFor(k.getBytes());
		
		key = Keys.hmacShaKeyFor(secret.getBytes()); // 위의 코드 2줄을 이렇게 한방에 해도됨
	}

	
	public String makeAccessToken(Authentication authentication) { // 유저의 이름과 패스워드, 그외 나머지들이 들어가있음
		return this.createToken(authentication, accessValidTime);
	}
	
	public String makeRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshValidTime);
	}
	
	// Token 발급
	private String createToken(Authentication authentication, Long validTime) {
		
		return Jwts
				.builder()
				// 사용자의 ID를 넣음(필수)
				.subject(authentication.getName()) 
				// 넣고 싶은 다른 정보들 넣기(옵션)
				.claim("roles", authentication.getAuthorities().toString()) 
				// Token 생성시간
				.issuedAt(new Date())
				// Token 유효시간
				.expiration(new Date(System.currentTimeMillis() + validTime))
				// 발급자(필수 아님)
				.issuer(issuer)
				// 암호화 알고리즘 키(가장중요) , 위의 정보들을
				.signWith(key)
				
				.compact() //에러 방지
				;
	}
	
	// Token 검증
	public Authentication getAuthenticationByToken(String token) throws Exception {
		
		// 검증
		Claims claims = 
				Jwts
				.parser() // 파싱
				.verifyWith(key) // 이 키로
				.build()
				.parseSignedClaims(token) //토큰을 파싱(키로 토큰을 제대로 만든거 맞냐?)
				.getPayload()
				;
		
		// 검증성공, 검증실패시 Exception 발생(여기서 처리안하고 그냥 던져버림)
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(claims.getSubject());
		
		UserDetails userDetails = userDAO.detail(userDTO);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); // 비밀번호는 안넣어도 되니까 null 넣어줌
		
		return authentication;
		
	}
	
	
}
