package com.winter.app.users;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class UserDTO {
	// 어노테이션이 있는건 다 검사함
	
	@NotBlank(groups = {RegisterGroup.class})
	private String username;
	@Pattern(
		    regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/~`]).{8,20}$",
		    message = "비밀번호는 영문, 숫자, 특수문자를 모두 포함하여 8~20자로 입력해야 합니다.",
    		groups = {RegisterGroup.class, PasswordGroup.class}
		)
	@NotBlank(groups = {RegisterGroup.class, PasswordGroup.class})
	private String password;
	
	private String passwordCheck; // 회원가입할때 패스워드 확인용
	@NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
	private String name;
	@Email(groups = {RegisterGroup.class, UpdateGroup.class})
	@NotBlank(groups = {RegisterGroup.class}) // 수정할때는 안보내도 통과하게끔
	private String email;
	@Pattern(
		    regexp = "^01[016789]-?\\d{3,4}-?\\d{4}$",
		    message = "올바른 휴대폰 번호 형식이 아닙니다. 000-0000-0000",
		    groups = {RegisterGroup.class, UpdateGroup.class}
		)
	private String phone;
	@Past(groups = {RegisterGroup.class, UpdateGroup.class}) // @Past는 날짜가 과거 이면 True , @Future는 날짜가 미래이면 True(숙박일정 쓸떄 쓰임)
	private LocalDate birth;
	private UserFileDTO userFileDTO;

}
