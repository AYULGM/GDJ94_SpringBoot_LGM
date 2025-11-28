package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardDTO;

@Mapper
public interface QnaDAO extends BoardDAO{
	
	public int refUpdate(BoardDTO boardDTO) throws Exception;
	
	public int stepUpdate(QnaDTO qnaDTO) throws Exception;
}
