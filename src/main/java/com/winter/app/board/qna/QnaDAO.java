package com.winter.app.board.qna;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.util.Pager;

@Mapper
public interface QnaDAO {
	
	public Long count() throws Exception;
	
	public List<QnaDTO> list(Pager pager) throws Exception;
	
	public int add(QnaDTO qnaDTO) throws Exception;
}
