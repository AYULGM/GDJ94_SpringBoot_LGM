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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/users/**")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Value("${category.user}")
	private String category;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;

	@ModelAttribute("category")
	private String getCategory() {
		return this.category;
	}
	
	// 나중에 postMapping으로 바꾸면 된다고 하심(25.12.11)
	@GetMapping("delete")
	public String delete(Authentication authentication) throws Exception {
		// 1. 일반회원(Cascade를 걸어줘서 같이 삭제하게하든가, 자식이 삭제되면 부모에 null을 넣게하든가, 컬럼하나 만들어서 삭제된 회원은 1을 넣던가)
		// 탈퇴 후 로그아웃을 진행(세션에 있는 Data가 남아있기 때문)
		
		// 2.소셜 로그인 (일반회원처럼 동일하게 하면됨) , 나중에 분기문 추가해야함
		// DB에서 작업
		WebClient webClient = WebClient.create();
		
		Mono<String> result = webClient.post()
										.uri("https://kapi.kakao.com/v1/user/logout") // 이건 단순 로그아웃 URI
										// https://kapi.kakao.com/v1/user/unlink // 이걸넣으면 연결해제(회원탈퇴)
										.header("Authorization", "KakaoAK "+adminKey) // KakaoAK하고 스페이스바 하나 넣어줘야하는거 주의 (문서 > 카카오 로그인 > REST API에서 로그아웃 목차)
										.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
										.body(BodyInserters.fromFormData("target_id_type", "user_id").with("target_id", authentication.getName()))
										.retrieve()
										.bodyToMono(String.class)
										;
				 
		System.out.println(result.block());
		
		return "redirect:./logout"; // redirect는 JSP가 아니라 URL처리임
				 
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
