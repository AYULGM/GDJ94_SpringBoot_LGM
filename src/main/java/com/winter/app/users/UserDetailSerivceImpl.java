package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailSerivceImpl implements UserDetailsService{
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("로그인 요청");
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		UserDetails userDetails;
		try {
			userDetails = userDAO.detail(userDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		return userDetails;
	}

}
