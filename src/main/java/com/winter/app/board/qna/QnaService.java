package com.winter.app.board.qna;

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
import com.winter.app.board.notice.NoticeFileDTO;
import com.winter.app.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Value("${app.upload.qna}")
	private String uploadPath;
	

	public List<BoardDTO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.count(pager);
		pager.pageing(totalCount);
		
		return qnaDAO.list(pager);
	}
	
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return qnaDAO.detail(boardDTO);
	}
	
	public int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		// 순서가 add를 먼저 호출하고 그다음에 업데이트 
		int result = qnaDAO.add(boardDTO);
		qnaDAO.refUpdate(boardDTO);
		
		for(MultipartFile f: attach) {
			File file = new File(uploadPath);
			if(!file.exists()) {
				file.mkdirs();
			}
			
			String fileName = UUID.randomUUID().toString();
			fileName = fileName+"_"+f.getOriginalFilename();
			
			file = new File(file, fileName);
			
			FileCopyUtils.copy(f.getBytes(), file);
			
			BoardFileDTO boardFileDTO = new NoticeFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setFileOrigin(f.getOriginalFilename());
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			qnaDAO.fileAdd(boardFileDTO);
		}
		
		return result;
	}

    @Override
	public int update(BoardDTO boardDTO) throws Exception {
		return qnaDAO.update(boardDTO);
	}
	
    @Override
    public int delete(BoardDTO boardDTO) throws Exception {
    	return qnaDAO.delete(boardDTO);
    }
    
	// BoardDTO로 받아도됨
	public int reply(QnaDTO qnaDTO) throws Exception{
		// 1. 부모의 정보를 조회
		QnaDTO parent =(QnaDTO) qnaDAO.detail(qnaDTO);
		// 2. 부모의 정보를 이용해서 step을 업데이트
		int result = qnaDAO.stepUpdate(parent);
		// 3. 부모의 정보를 이용해서 ref, step, depth를 세팅
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep()+1);
		qnaDTO.setBoardDepth(parent.getBoardDepth()+1);
		// 4. insert
		result = qnaDAO.add(qnaDTO);
		
		return result;
	}

	
}
