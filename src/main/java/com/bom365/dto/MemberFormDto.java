package com.bom365.dto;


import javax.validation.constraints.NotBlank;
import com.bom365.constant.Duplicate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString
public class MemberFormDto {
		
		@NotBlank(message="아이디를 입력해 주세요")
		private String supporterId;
	
	 	@NotBlank(message = "이름은 필수 입력 값입니다.")
	 	private String supporterName;
	 	
	 	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	 	private String email;
	 	
	    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	    private String supporterPassword;

	    @NotBlank(message = "휴대폰 번호는 필수 입력 값입니다.")
	    private String phoneNumber;

	    @NotBlank(message = "우편주소는 필수 입력 값입니다.")
	    private String postCode;

	    @NotBlank(message = "상세 주소는 필수 입력 값입니다.")
	    private String detailedAddress;
	    
	    @NotBlank(message = "아이디 확인은 필수 입니다.")
	    private String duplicate;
	    
	    
	    public boolean isDuplicateCheck() {
			return this.duplicate.equals("duplicate");
		}
}
