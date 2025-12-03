package com.winter.app.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 객체가 만들어지는 어노테이션
@Component //이름 지정안해주면 앞글자가 소문자인 fileDownView (우선순위는 JSP찾는거보다 높음)
public class FileDownView extends AbstractView{

	//BeanNameViewResolver
	
	@Value("${app.upload.base}")
	private String filePath;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// Set의 특징 순서유지x 중복x
		// Set의 형식을 iterator 형식으로 바꿔주는것.(key값이 String이라 <String>으로 받아줌)
//		Iterator<String> it = model.keySet().iterator(); 
//		
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		FileDTO fileDTO = (FileDTO)model.get("file");
		String category = (String)model.get("category");
		
		File file = new File(filePath+category, fileDTO.getFileName());
		
		//한글처리(깨질 수 있기때문에)
		response.setCharacterEncoding("UTF-8");
		
		//총 파일의 크기(안넣어주면 다운로드 받을때 다운로드 받는시간이 알수 없음이라뜸 , 파일의 총 크기/다운로드속도 = 다운로드예상시간)
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일명을 인코딩(파일명은 오리진파일네임으로 하는게 깔끔하지, 파일다운로드받을때도 한글 안깨지게)
		String origin = URLEncoder.encode(fileDTO.getFileOrigin(), "UTF-8");
		
		//header 설정(정해져 있는 템플릿, 전부 노션에 있다고 하심)
		response.setHeader("Content-Disposition", "attachment;filename=\""+origin+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//Server의 HDD에서 파일을 가져와서 (0과 1을 읽어옴)
		FileInputStream fi = new FileInputStream(file);
		//Client한테 전송하는것만 하면됨
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fi, os);
		
		//자원 해제 (연결된 순서의 역순)
		os.close();
		fi.close();
		// flush(강제전송)는 버퍼에 담긴애들이 아니기때문에 안해줘도됨
		
		System.out.println(fileDTO);
		System.out.println(category);
		
		System.out.println("Custom View");
	}

}
