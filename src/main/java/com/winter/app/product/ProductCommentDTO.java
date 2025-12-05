package com.winter.app.product;

import com.winter.app.board.CommentDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ProductCommentDTO extends CommentDTO {
	
	private Long productNum;
	private String username;
}
