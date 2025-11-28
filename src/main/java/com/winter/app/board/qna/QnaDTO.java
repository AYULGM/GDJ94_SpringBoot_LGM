package com.winter.app.board.qna;

import com.winter.app.board.BoardDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// Ref,Step,Depth는 private이라 게터,세터,투스트링해줘야함
public class QnaDTO extends BoardDTO{

	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
	
}
