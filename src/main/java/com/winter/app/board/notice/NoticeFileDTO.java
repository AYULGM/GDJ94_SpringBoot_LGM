package com.winter.app.board.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeFileDTO{

	private Long fileNum;
	private String fileName;
	private String fileOrigin;
	private Long username;
}
