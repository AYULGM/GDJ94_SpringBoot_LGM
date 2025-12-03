package com.winter.app.board;

import java.util.List;

import com.winter.app.util.Pager;

public interface BoardDAO {

	public Long count(Pager pager) throws Exception; // 매개변수 건네줄게 없음
	
	public List<BoardDTO> list(Pager pager) throws Exception;
	
	public BoardDTO detail(BoardDTO boardDTO) throws Exception;
	
	public int add(BoardDTO boardDTO) throws Exception;
	
	public int delete(BoardDTO boardDTO) throws Exception;
	
	public int update(BoardDTO boardDTO) throws Exception;
	
	public int fileAdd(BoardFileDTO boardFileDTO) throws Exception;
	
	public int fileDelete(BoardDTO boardDTO) throws Exception;
	
	public BoardFileDTO fileDetail(BoardFileDTO boardFileDTO) throws Exception;
}
