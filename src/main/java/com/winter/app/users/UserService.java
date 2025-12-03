package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManager;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.user}")
	private String uploadPath;
	
	public int register(UserDTO userDTO, MultipartFile profile)throws Exception{
		int result=0;
		
		result = userDAO.register(userDTO);
		
		if(profile == null || profile.isEmpty()) {
			return result;
		}
		
		File file = new File(uploadPath);
		
		String fileName = fileManager.fileSave(file, profile);
		
		UserFileDTO userFileDTO = new UserFileDTO();
		userFileDTO.setUsername(userDTO.getUsername());
		userFileDTO.setFileName(fileName);
		userFileDTO.setFileOrigin(profile.getOriginalFilename());
		
		userDAO.userFileAdd(userFileDTO);
		
		return result;
	}
	public UserDTO detail(UserDTO userDTO)throws Exception{ //mypage와 로그인 둘다 쓰임
		UserDTO loginDTO = userDAO.detail(userDTO);
		
		// 아이디를 조회해서 있으면 비번 비교해서 맞으면 로그인 아니면 null을 집어넣는다.
		if(loginDTO != null) {
			if(loginDTO.getPassword().equals(userDTO.getPassword())) {
				return loginDTO;
			}else {
				loginDTO = null;
			}
		}
		
		return loginDTO;
	}
	
}
