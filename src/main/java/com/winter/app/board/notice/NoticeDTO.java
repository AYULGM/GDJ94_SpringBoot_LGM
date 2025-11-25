package com.winter.app.board.notice;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDTO { 
	//DB에서는 _으로 잇지만 자바에서는 카멜케이스로 적는다.
	private Long boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private LocalDate boardDate;
	private Long boardHit;
	
}
