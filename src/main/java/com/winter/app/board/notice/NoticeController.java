package com.winter.app.board.notice;

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
import com.winter.app.board.BoardFileDTO;
import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/notice/*")
@Slf4j // log보려고 썼는데 지웠음
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	// spring에서 제공하는 value어노테이션을 사용(롬복말고)
	// Value 어노테이션은 properties파일들을 저격해서 찾아감(이게 문법임, = 써서 ${}집어넣을생각 X)
	@Value("${category.board.notice}")
	private String category;
	
	@ModelAttribute("category") // 모든 메서드마다 Model을 집어넣는걸 대신해줌
	public String getCategory() {
		return this.category;
	}
	
	@GetMapping("list")
	// public String list(@RequestParam(defaultValue = "1") Long page, Model model) throws Exception{ // Long page의 기본값을 1로 설정(참조타입 기본값인 null 방지)
		public String list(Pager pager, Model model) throws Exception{ // Pager 객체안에 page를 넣었음
		// Pager pager = new Pager(); // Heap에 Pager 객체를 만들어라. (참고로 지역변수는 Stack에 만들어짐)
		// List<NoticeDTO> ar = noticeService.list(page, pager);
		List<BoardDTO> ar = noticeService.list(pager); //pager안에 page를 넣었으니 둘이 합쳐서 pager하나만 매개변수 받음
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String detail(BoardDTO boardDTO,  Model model) throws Exception {
		
		boardDTO = noticeService.detail(boardDTO);
		
		// null(조회 실패시 처리는 배운대로 알아서)
		if(boardDTO == null) {
			
		}
		
		// jsp로 보내려면 model이 필요하다.
		model.addAttribute("dto", boardDTO);
		return "board/detail";
	}
	
	// 내가한거
//	@GetMapping("add")
//	public String add() {
//		return "notice/add";
//	}
	
	// 쌤이한거
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}

	
	// 내가한거
//	@PostMapping("add")
//	public String add(NoticeDTO noticeDTO) throws Exception {
//		System.out.println(noticeDTO.getBoardWriter());
//		System.out.println(noticeDTO.getBoardTitle());
//		System.out.println(noticeDTO.getBoardContents());
//		noticeService.add(noticeDTO);
//		
//		return "redirect:/notice/list";
//	}
	
	// 썜이한거
	@PostMapping("add")
	// BoardDTO로도 받아도된다. 어차피 넘어오는 값이 공통된 3개 Writer,Title,Contents.
	// (25.12.01) 5개까지 받으니 배열로선언
	public String add(NoticeDTO noticeDTO, MultipartFile[] attach) throws Exception {
		int result = noticeService.add(noticeDTO, attach);
		
		return "redirect:./list";
	}
	
	@GetMapping("update")
	public String update(BoardDTO boardDTO, Model model) throws Exception { //noticeDTO도 됨

	    // 기존 글 정보 가져오기
	    boardDTO = noticeService.detail(boardDTO);
	    // noticeDTO = (NoticeDTO)noticeService.detail(noticeDTO); 이렇게 해도됨

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
	//noticeDTO라고 해도됨 이것두..
	public String update2(BoardDTO boardDTO) throws Exception {

	    int result = noticeService.update(boardDTO);

//	    String message = (result > 0) ? "글 수정 성공" : "글 수정 실패";
//
//	    model.addAttribute("msg", message);
//	    model.addAttribute("path", "./list");

	    return "redirect:./detail?boardNum=" + boardDTO.getBoardNum();
	}
	
	@PostMapping("delete")
	public String delete(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.delete(noticeDTO);
		
		return "redirect:./list";
	}
	
	@GetMapping("fileDown")
	public String fileDown(BoardFileDTO boardFileDTO, Model model) throws Exception {
		boardFileDTO = noticeService.fileDetail(boardFileDTO);
		model.addAttribute("file", boardFileDTO);
		
		return "fileDownView"; // ModelAndView에 담겨서 리턴됨
	}
}
