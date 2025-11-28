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
	// 방법2. 원시타입으로 바꿔서 0으로 초기화
	private long boardDepth;
	
	// 방법3. 롬복이 만드는게 아니라 우리가 직접 만들어서 수정하는 방법
	public Long getBoardRef() {
		if(this.boardRef == null) {
			this.boardRef = 0L;
		}
		return this.boardRef;
	}
}
