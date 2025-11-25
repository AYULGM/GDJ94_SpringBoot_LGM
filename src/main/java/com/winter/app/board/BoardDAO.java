package com.winter.app.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDAO { // 간혹 BoardMapper라고 쓰는 사람도 있다.

	// 목록
	public List<BoardDTO> list() throws Exception;
	
	// 상세정보
	public BoardDTO detail(BoardDTO boardDTO) throws Exception;
	// Long num을 받을 수있겠지만 BoardDTO로 받는다.
	
	// Mapper는 패키지명.클래스명
	// Result 타입 : BoardDTO
	// id : detail
	// parameterType : BoardDTO

	
	// 글 추가(Create) , rows를 반환하기때문에 int (title,writer,contents)가 넘어가야하기 때문에 BoardDTO
	public int add(BoardDTO boardDTO) throws Exception;
	
	// public int delete(BoardDTO boardDTO) throws Exception;
	// 삭제 (위처럼 쓰는게 아닌 아래 식으로 쓸 수도 있다. 상황에 맞게 쓰는게 중요)
	public int delete(Long num2) throws Exception;
	
	// 글 수정(Update)
	// public int update(BoardDTO boardDTO) throws Exception;
	public int update(Map<String, Object> map) throws Exception;
	
}
