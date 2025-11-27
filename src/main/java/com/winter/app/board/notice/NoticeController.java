package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.util.Pager;

import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/notice/*")
@Slf4j // log보려고 썼는데 지웠음
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("list")
	// public String list(@RequestParam(defaultValue = "1") Long page, Model model) throws Exception{ // Long page의 기본값을 1로 설정(참조타입 기본값인 null 방지)
		public String list(Pager pager, Model model) throws Exception{ // Pager 객체안에 page를 넣었음
		// Pager pager = new Pager(); // Heap에 Pager 객체를 만들어라. (참고로 지역변수는 Stack에 만들어짐)
		// List<NoticeDTO> ar = noticeService.list(page, pager);
		List<NoticeDTO> ar = noticeService.list(pager); //pager안에 page를 넣었으니 둘이 합쳐서 pager하나만 매개변수 받음
		
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		
		return "notice/list";
	}
	
	@GetMapping("detail")
	public void detail(NoticeDTO noticeDTO,  Model model) throws Exception {
		
		noticeDTO = noticeService.detail(noticeDTO);
		
		// null(조회 실패시 처리는 배운대로 알아서)
		if(noticeDTO == null) {
			
		}
		
		// jsp로 보내려면 model이 필요하다.
		model.addAttribute("dto", noticeDTO);
		// return "notice/detail";
	}
	
	// 내가한거
//	@GetMapping("add")
//	public String add() {
//		return "notice/add";
//	}
	
	// 쌤이한거
	@GetMapping("add")
	public void add1() throws Exception {}
	// 저절로 URL따라감
	
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
	public String add1(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.add(noticeDTO);
		
		return "redirect:./list";
	}
	
}
