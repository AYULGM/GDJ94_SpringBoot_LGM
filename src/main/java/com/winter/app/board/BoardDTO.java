package com.winter.app.board;

import java.time.LocalDate;

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
}
