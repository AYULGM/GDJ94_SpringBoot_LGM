package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardDTO;

@Mapper
public interface QnaDAO extends BoardDAO{
	
	public int refUpdate(BoardDTO boardDTO) throws Exception;
	
	//접근지정자 데이터타입 메서드명(매개변수)
}
