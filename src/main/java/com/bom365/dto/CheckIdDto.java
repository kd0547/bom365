package com.bom365.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CheckIdDto {
	
	
	@NotBlank(message="아이디를 입력해 주세요")
	@Length(min=4,max=16, message="최소 4자 , 최대 20 자 입니다.")
	@Pattern(regexp="^[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$", message="허용되지 않는 문자열이 포함되어 있습니다.")
	@Pattern(regexp="^[0-9|a-z|A-Z]*$", message="한글이 포함되어 있습니다.")
	@Pattern(regexp="^[a-z|A-Z]+[0-9]*$", message="첫글자는 영문이어야 합니다.")
	//@Pattern(regexp="^[a-z|A-Z]*$", message="첫글자는 영문이어야 합니다.")
	private String id;
	
}
