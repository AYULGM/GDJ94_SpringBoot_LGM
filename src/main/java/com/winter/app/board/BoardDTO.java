package com.winter.app.board;

import java.time.LocalDate;
import java.util.List;

import com.winter.app.board.notice.NoticeFileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 게시판관련 DTO의 부모로 사용
@Getter
@Setter
@ToString
public class BoardDTO {

	private Long boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private LocalDate boardDate;
	private Long boardHit;
	
	// has a(가지고있다)관계
	// 파일이 5개니까.
	private List<BoardFileDTO> fileDTOs; // notice도 쓰고 qna도 써서 부모인 여기에 적음
}
