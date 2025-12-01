package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDTO;
import com.winter.app.board.notice.NoticeDTO;
import com.winter.app.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@Value("${category.board.qna}")
	// spring에서 제공하는 value어노테이션을 사용(롬복말고)
	// properties에서 자바쪽으로 땡겨올때 @Value를 사용
	private String category;
	
	@ModelAttribute("category") // 모든 메서드마다 Model을 집어넣는걸 대신해줌(모델 객체에 넣는다고 생각해도 됨,모델은 즉 request니까)
	public String getCategory() {
		return this.category;
	}
	
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
	public String add(QnaDTO qnaDTO, MultipartFile[] attach) throws Exception{
		// 0으로 초기화하려면 이렇게 set해서 코드 3줄넣거나, QnaDTO.java에서 참조타입의 기본값이 null이니 원시타입(long)으로
		// 바꿔주면 기본값이 0이니 Long -> long으로 바꾸거나 아니면 QnaDTO.java에서 getter로 넘겨줄때 조건문(0일때)를 건다.
		// 총 3가지 방법(더있긴한데)중에 고르면 됨
		// 방법1.
		qnaDTO.setBoardRef(0L);
		qnaDTO.setBoardDepth(0L);
		qnaDTO.setBoardStep(0L);
		int result = qnaService.add(qnaDTO, attach);
		BoardDTO boardDTO = qnaDTO;
		return "redirect:./list";
	}
	
	@GetMapping("reply")
	public String reply(QnaDTO qnaDTO, Model model) throws Exception {
		model.addAttribute("dto", qnaDTO);
		
		return "board/add"; // 어차피 writer,title,contents 쓰는거 똑같아서재활용
	}
	
	@PostMapping("reply")
	public String reply(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.reply(qnaDTO);
		
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(BoardDTO boardDTO, Model model) throws Exception { //qnaDTO도 됨

	    // 기존 글 정보 가져오기
	    boardDTO = qnaService.detail(boardDTO);
	    // qnaDTO = (NoticeDTO)qnaService.detail(qnaDTO); 이렇게 해도됨

//	    if(boardDTO == null) {
//	        model.addAttribute("msg", "글이 존재하지 않습니다.");
//	        model.addAttribute("path", "./list");
//	        return "commons/result";
//	    }

	    // JSP로 전달
	    model.addAttribute("dto", boardDTO);
	    model.addAttribute("sub", "Update"); //추가

		
//		 return "board/update";
		  return "board/add";
	}
	
	@PostMapping("update")
	public String update2(BoardDTO boardDTO) throws Exception {

	    int result = qnaService.update(boardDTO);

//	    String message = (result > 0) ? "글 수정 성공" : "글 수정 실패";
//
//	    model.addAttribute("msg", message);
//	    model.addAttribute("path", "./list");

	    return "redirect:./detail?boardNum=" + boardDTO.getBoardNum();
	}
	
	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		
		return "redirect:./list";
	}
}
