package com.winter.app.board;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
	
	private Long boardNum;
	private String boardContents;
	private LocalDate boardDate;
}
