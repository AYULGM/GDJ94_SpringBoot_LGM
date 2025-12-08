package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public boolean getError(UserDTO userDTO, BindingResult bindingResult)throws Exception{
		//check : true  -> 검증 실패, error 존재
		//check : flase -> 검증 성공, error 존재 X
		//1. annotation 검증 결과
		boolean check = bindingResult.hasErrors();
		
		//2. password 일치 하는지 검증
		if(!userDTO.getPassword().equals(userDTO.getPasswordCheck())) {
			check=true;
			//bindingResult.rejectValue("멤버변수명", "properties의 키");
			bindingResult.rejectValue("passwordCheck", "user.password.equal");
		}
		
		//3. ID 중복 체크
		if(userDTO.getUsername() != null) {
			UserDTO checkDTO = userDAO.detail(userDTO);
			if(checkDTO != null) {
				check=true;
				bindingResult.rejectValue("username", "user.username.duplication");
			}
		}
		
		return check;
	}
	
	public int register(UserDTO userDTO, MultipartFile profile)throws Exception{
		int result=0;
		
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		result = userDAO.register(userDTO);
		
		result = userDAO.roleAdd(userDTO);
		
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
	public UserDTO detail(UserDTO userDTO)throws Exception{
		UserDTO loginDTO = userDAO.detail(userDTO);
		
		if(loginDTO != null) {
			if(loginDTO.getPassword().equals(userDTO.getPassword())) {
				return loginDTO;
			}else {
				loginDTO = null;
			}
		}
		
		
		return loginDTO;
	}
	
	public int update(UserDTO userDTO)throws Exception{
		return userDAO.update(userDTO);
	}
	
	
	
}
