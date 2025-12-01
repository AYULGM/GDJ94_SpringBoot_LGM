package com.winter.app.board.notice;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
import com.winter.app.board.BoardService;
import com.winter.app.util.Pager;

@Service
public class NoticeService implements BoardService{

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Value("${app.upload.notice}") //VALUE 어노테이션으로 EL형식으로 가져온다.
	private String uploadPath;
	
		@Override
		public List<BoardDTO> list(Pager pager) throws Exception {
			// 1. totalCount 구하기
			Long totalCount = noticeDAO.count(pager);
			pager.pageing(totalCount);
			
		return noticeDAO.list(pager);
	}
	
    @Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return noticeDAO.detail(boardDTO);
	}
    @Override
	public int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
    	
    	int result = noticeDAO.add(boardDTO);
    	
    	//  파일을 HDD에 저장할건데
    	//  1. 어디에 저장?
    	//  2. 어떤 이름으로 저장?
    	for(MultipartFile f: attach) { // for-each문
    		File file = new File(uploadPath);
    		if(!file.exists()) {
    			file.mkdirs();
    		}
    		
    		String fileName = UUID.randomUUID().toString();
    		fileName = fileName+"_"+f.getOriginalFilename();
    		
    		file = new File(file, fileName);
    		
    		// 3. 파일 저장
    		FileCopyUtils.copy(f.getBytes(), file);
    		
    		// 4. 정보를 DB에 저장
    		BoardFileDTO boardFileDTO = new NoticeFileDTO(); // notice로 만들어서 부모인 Board에 넣는게 가능함.
    		boardFileDTO.setFileName(fileName);
    		boardFileDTO.setFileOrigin(f.getOriginalFilename());
    		boardFileDTO.setBoardNum(boardDTO.getBoardNum()); // 여기서 NoticeDAO.xml으로부터 증가된 boardNum값을 받아옴
    		noticeDAO.fileAdd(boardFileDTO);
    	}
    	
		return result; //noticeDAO.add(boardDTO);
	}
    @Override
	public int delete(BoardDTO boardDTO) throws Exception {
		return noticeDAO.delete(boardDTO);
	}
    @Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}
	
	
}
