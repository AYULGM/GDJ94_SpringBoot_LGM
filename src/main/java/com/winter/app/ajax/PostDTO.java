package com.winter.app.ajax;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PostDTO {

	private Integer userId;
	private Integer id;
	private String title;
	private String body;
}
