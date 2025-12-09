package com.winter.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public void detail(@AuthenticationPrincipal UserDTO userDTO, Model model)throws Exception{ // (UserDTO userDTO,Model model)
		
		userDTO = userService.detail(userDTO);
		model.addAttribute("user", userDTO);
	}
	
	@GetMapping("login")
	public String login(HttpSession session) throws Exception {
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT"); // 로그인하면 SPRING_SECURITY_CONTEXT가 생성되므로 써보자.
		
		if(obj != null) { // 로그인 상태라면
			return "redirect:/"; // 뒤로가기 했을때 다시 로그인창으로 가는거 방지
		}
		
		return "users/login"; // 로그인 상태가 아니라면
	}
		
	

	
	@GetMapping("update")
	public void update(@AuthenticationPrincipal UserDTO userDTO ,Model model) throws Exception{
		
		model.addAttribute("userDTO", userDTO);
	}
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserDTO userDTO,BindingResult bindingResult, Authentication authentication) throws Exception{
		if(bindingResult.hasErrors()) {
			return "users/update";
		}
		
		userDTO.setUsername(authentication.getName());
		
		int result = userService.update(userDTO);
		
//		if(result > 0) {
//			// DB에서 사용자를 조회 해야 함
//			UsernamePasswordAuthenticationToken to = new UsernamePasswordAuthenticationToken(userDTO, authentication.getCredentials(), authentication.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(to);
//		}
		
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
