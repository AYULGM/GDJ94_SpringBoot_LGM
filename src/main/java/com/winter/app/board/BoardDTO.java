package com.winter.app.board;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 게시판관련 DTO의 부모로 사용
@Getter
@Setter
@ToString
public class BoardDTO extends CommentDTO {

	@NotBlank(message = "필수에요 제발 적어주세요")
	/* @Size(min = 1) 어차피 NotBlank에 포함되어있음 */
	private String boardTitle;
	private String boardWriter;
	private Long boardHit;
	
	// has a(가지고있다)관계
	// 파일이 5개니까.
	private List<BoardFileDTO> fileDTOs; // notice도 쓰고 qna도 써서 부모인 여기에 적음
}
