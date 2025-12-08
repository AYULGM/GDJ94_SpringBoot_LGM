package com.winter.app.users;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
	
	public int register(UserDTO userDTO)throws Exception;
	public int userFileAdd(UserFileDTO userFileDTO)throws Exception;
	public int roleAdd(UserDTO userDTO)throws Exception;
	
	public UserDTO detail(UserDTO userDTO)throws Exception;
	
	public int update(UserDTO userDTO)throws Exception;

}
