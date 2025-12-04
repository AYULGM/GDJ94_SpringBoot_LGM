package com.winter.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	
	// bindingResult는 에러를 담는 클래스
	public boolean getError(UserDTO userDTO,BindingResult bindingResult) throws Exception{
		// check: true -> 검증 실패, error 존재
		// check: false -> 검증 성공, error 존재 X
		// 1. annotation 검증 결과
		boolean check = bindingResult.hasErrors();
		
		// 2. password 일치 하는지 검증
		if(!userDTO.getPassword().equals(userDTO.getPasswordCheck())) {
			check = true;
			//bindingResult.rejectValue("멤버변수명", "properties의 키");
			bindingResult.rejectValue("passwordCheck", "user.password.equal");
		}
		
		//3. ID 중복체크
//		DB에서 데이터를 조회해서 데이터가 있으면 check에다 true를 넣어주고 아니면 안넣어준다. 위에거랑 같음
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
	
	public int update(UserDTO userDTO) throws Exception{
		return userDAO.update(userDTO);
	}
	
	
}
