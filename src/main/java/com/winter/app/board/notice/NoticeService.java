package com.winter.app.board.notice;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.BoardFileDTO;
import com.winter.app.board.BoardService;
import com.winter.app.files.FileManager;
import com.winter.app.util.Pager;

@Service
public class NoticeService implements BoardService{

	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired //Autowired는 변수마다 하나씩 지정해줘야함.
	private FileManager fileManager;
	
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
    	// 글번호가 필요
    	int result = noticeDAO.add(boardDTO);
    	
    	if(attach == null) {
    		return result;
    	}
    	
    	//  파일을 HDD에 저장할건데
    	//  1. 어디에 저장?
    	//  2. 어떤 이름으로 저장?
    	File file = new File(uploadPath); // 파일객체는 한번만 있으면 됨
    	for(MultipartFile f: attach) { // for-each문
    		if(f == null || f.isEmpty()) { // 이 코드가 없으면 예외 발생 가능성
    			continue;
    		}
    		String fileName = fileManager.fileSave(file, f);
    		
    		// 4. 정보를 DB에 저장
    		BoardFileDTO boardFileDTO = new BoardFileDTO(); // notice로 만들어서 부모인 Board에 넣는게 가능함.(NoticeFileDTO()도 가능하다)
    		boardFileDTO.setFileName(fileName);
    		boardFileDTO.setFileOrigin(f.getOriginalFilename());
    		boardFileDTO.setBoardNum(boardDTO.getBoardNum()); // 여기서 NoticeDAO.xml으로부터 증가된 boardNum값을 받아옴
    		noticeDAO.fileAdd(boardFileDTO);
    	}
    	
		return result; //noticeDAO.add(boardDTO);
	}
    @Override
	public int delete(BoardDTO boardDTO) throws Exception {
    	// 지우기전에 디테일 불러옴
    	boardDTO = noticeDAO.detail(boardDTO);
    	// HDD에서 파일을 삭제
    	if(boardDTO.getFileDTOs() != null) {
    	for(BoardFileDTO boardFileDTO:boardDTO.getFileDTOs()) {
    		File file = new File(uploadPath, boardFileDTO.getFileName());
    		boolean flag = fileManager.fileDelete(file);
    	}
    	}
    	
    	// -----------------
    	// 순서 중요(파일먼저 지우고 그다음에 보드 지움)
    	int result = noticeDAO.fileDelete(boardDTO);
		return noticeDAO.delete(boardDTO);
	}
    
    @Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}
    
    @Override
    public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO) throws Exception {
    	
    	return noticeDAO.fileDetail(boardFileDTO);
    }
	
	
}
