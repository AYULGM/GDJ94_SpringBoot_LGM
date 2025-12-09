package com.winter.app.users;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class UserDTO implements UserDetails{
	
	@NotBlank(groups = {RegisterGroup.class})
	private String username;
	//@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])[A-Za-z0-9#?!@$%^&*-]{8,12}$")
	
	@NotBlank(groups = {RegisterGroup.class, PasswordGroup.class})
	private String password;
	
	
	private String passwordCheck;
	
	@NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
	private String name;
	@Email(groups = {RegisterGroup.class, UpdateGroup.class})
	@NotBlank(groups = {RegisterGroup.class})
	private String email;
	@Pattern(regexp = "^01(?:0|1|[6-9])-[0-9]{3,4}-[0-9]{4}$", groups = {RegisterGroup.class, UpdateGroup.class})
	private String phone;
	@Past(groups = {RegisterGroup.class, UpdateGroup.class})
	private LocalDate birth;
	private UserFileDTO userFileDTO;
	
	private List<RoleDTO> roleDTOs;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> list= new ArrayList<>();
		
		for(int i=0;i<roleDTOs.size();i++) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleDTOs.get(i).getRoleName());
			list.add(g);
		}
		
		return list;
	}
	
	// 어차피 @게터@세터 있으니까 아래는 다 주석처리했슴..
	// 아래 4개중에 1개라도 return값에 false가 있으면 로그인 실패(주석으로 false로 바꿨을떄 메시지를 써놓음)
	
//	@Override
//	public boolean isAccountNonExpired() { // 회원이 사용할 수 있는기간 ,   DB컬럼에 넣을때는 is넣는거아님, account_non_expired, 타입은 bit)
//		// 사용자 계정의 유효 기간이 만료 되었습니다.
//		// AccountExpiredException
//		return true;
//	}
//	@Override
//	public boolean isAccountNonLocked() { // 예를들어 5회 틀리면 계정잠기게하는거임
//		// 사용자 계정이 잠겨 있습니다.
//		// LockedException
//		return true;
//	}
//	@Override
//	public boolean isCredentialsNonExpired() { // 비번에 유효기간 주는거임(임시비밀번호로 3일 유예주는거)
//		// 자격 증명 유효 기간이 만료되었습니다.
//		// CredentialsExpiredException
//		return true;
//	}
//	@Override
//	public boolean isEnabled() { // 계정이 활성화되었는지(처리에 따라 삭제or휴면) 
//		// 유효하지 않은 사용자입니다.
//		// DisabledException
//		return true;
//	}
	
	

}
