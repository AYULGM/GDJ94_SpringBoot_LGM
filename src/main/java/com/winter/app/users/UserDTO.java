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
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
