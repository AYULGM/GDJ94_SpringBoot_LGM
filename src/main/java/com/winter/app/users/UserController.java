package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users/**")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Value("${category.user}")
	private String category;

	@ModelAttribute("category")
	private String getCategory() {
		return this.category;
	}
	
	@GetMapping("register")
	public void register()throws Exception{}	
	
	
	@PostMapping("register")
	public String register(UserDTO userDTO, MultipartFile attach)throws Exception{
		int result = userService.register(userDTO, attach);
		
		return "redirect:/";
	}
	@GetMapping("mypage")
	public void detail(UserDTO userDTO,Model model)throws Exception{
		userDTO = userService.detail(userDTO);
		model.addAttribute("user", userDTO);
	}
	
	@GetMapping("login")
	public void login() throws Exception {}
	
	@PostMapping("login")
	//유저네임,패스워드 파라미터가 넘어와서 userDTO에 담기게
	//request보다 session이 더크니까 편한 session
	public String login(UserDTO userDTO, HttpSession session) throws Exception { 
		
		userDTO = userService.detail(userDTO);
		
		// 처리는 알아서
		if (userDTO == null) {
			
		}
		
		session.setAttribute("user", userDTO);
		
		return "redirect:/";
	}
	
}
