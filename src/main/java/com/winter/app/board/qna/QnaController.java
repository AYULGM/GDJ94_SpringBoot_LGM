package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardDTO;
import com.winter.app.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> ar = qnaService.list(pager);
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(BoardDTO boardDTO, Model model) throws Exception {
		
		boardDTO = qnaService.detail(boardDTO);
		
		if(boardDTO == null) {
			
		}
		
		model.addAttribute("dto", boardDTO);
		return "board/detail";
	}
	
	@GetMapping("add")
	public String add() throws Exception{
		
		return "board/add"; // 안써주면 qna/add로 가려던걸 다르게 지정함
	}
	
	@PostMapping("add")
	// writer title contents 받음. Board로 받아도되고 Qna로 받아도되고
	public String add(QnaDTO qnaDTO) throws Exception{
		int result = qnaService.add(qnaDTO);
		
		return "redirect:./list";
	}
	
	// 리스트에서 글쓰기버튼을 클릭하면 질문글을 입력하는 폼이 나오게
	
}
