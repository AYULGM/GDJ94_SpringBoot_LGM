package com.winter.app.files;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileDTO {

	private Long fileNum;
	private String fileName;
	private String fileOrigin;
}
