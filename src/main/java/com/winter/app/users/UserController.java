package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

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
	//@ModelAttribute는 숨겨져있는거임
	public String register(@ModelAttribute UserDTO userDTO)throws Exception{
		return "users/register";
	}	
	
	
	@PostMapping("register")
	// 지정할땐 @Valid가 아니라 @Validated
	public String register(@ModelAttribute @Validated(RegisterGroup.class) UserDTO userDTO,BindingResult bindingResult,MultipartFile attach)throws Exception{
		
		if(userService.getError(userDTO, bindingResult)) {
			
			return "users/register";
		}
		
		int result = userService.register(userDTO, attach); // 실제로 DB에 들어가는 코드
		
		return "redirect:/";
	}
	@GetMapping("mypage")
	public void detail()throws Exception{ // (UserDTO userDTO,Model model)
//		userDTO = userService.detail(userDTO);
//		model.addAttribute("user", userDTO);
	}
	
	@GetMapping("login")
	public void login() throws Exception {}
	

	
	@GetMapping("update")
	public void update(HttpSession session,Model model) throws Exception{
		
		model.addAttribute("userDTO", session.getAttribute("user"));
	}
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserDTO userDTO,BindingResult bindingResult, HttpSession session) throws Exception{
		if(bindingResult.hasErrors()) {
			return "users/update";
		}
		
		UserDTO loginDTO = (UserDTO)session.getAttribute("user");
		userDTO.setUsername(loginDTO.getUsername());
		
		int result = userService.update(userDTO);
		
		if(result > 0) {
			loginDTO = userService.detail(loginDTO);
			session.setAttribute("user", loginDTO);
		}
		
		return "redirect:/";
	}
	
	// 비밀번호 검증을 위한 페이지
	@GetMapping("change")
	public void change(UserDTO userDTO) throws Exception {
		
	}
	
	// 비밀번호 검증을 위한 페이지
	@PostMapping("change")
	public String change(@Validated(PasswordGroup.class) UserDTO userDTO,BindingResult bindingResult , String exist) throws Exception {
		
		if(userService.getError(userDTO, bindingResult)) {
			return "users/change";
		}
		
		return "redirect:mypage";
	}
	
}
