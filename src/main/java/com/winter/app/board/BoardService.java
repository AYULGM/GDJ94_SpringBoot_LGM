package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.util.Pager;

public interface BoardService {
	// count는 메서드 내에서 쓰는거기때문에 BoardDAO에서 안데려옴
	public List<BoardDTO> list(Pager pager) throws Exception;
	
	public BoardDTO detail(BoardDTO boardDTO) throws Exception;
	
	public int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception;
	
	public int delete(BoardDTO boardDTO) throws Exception;
	
	public int update(BoardDTO boardDTO) throws Exception;
}
