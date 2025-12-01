package com.winter.app.board;

import com.winter.app.files.FileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileDTO extends FileDTO{

	private Long boardNum; // 얘 하나 때문에 여기서도 게터세터투스트링 만들어줌
}
