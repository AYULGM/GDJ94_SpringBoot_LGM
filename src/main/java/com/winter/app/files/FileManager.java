package com.winter.app.files;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

// NoticeService,QnaService 둘다 공통된 부분을 여기로 뺐음.
@Component //DAO,DTO,Service도 아니라 Component
public class FileManager {
	
	public boolean fileDelete(File file) throws Exception{
		// 1. 어느 경로에 어떤 파일명을 지울 것인가?
		return file.delete();
	}
	
	//TDD(Test Driven Development,에러를 발생시키면서 코드를 짜는 방법)
	public String fileSave(File file, MultipartFile f) throws Exception { //지역변수로 선언해도 되는데 매개변수로 받아오기로함그냥
		if(!file.exists()) {
			file.mkdirs();
		}
		
		// 저장할 파일명 생성 확장자 포함
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+"_"+f.getOriginalFilename();
		
		// HDD에 파일 저장
		file = new File(file, fileName);
		// 3. 파일 저장
//		FileCopyUtils.copy(f.getBytes(), file);
		f.transferTo(file); // 위 코드말고 이 코드도 된다. 편한대로 쓰셈
		
		return fileName;
	}
	
}
