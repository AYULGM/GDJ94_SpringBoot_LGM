package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
