package com.winter.app.board;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {

	private Long num;
	private String title;
	private String writer;
	private String contents;
	private LocalDate date;
	private Long hit;
}
