package com.bom365.dto;

import java.time.LocalDateTime;


import javax.validation.constraints.NotBlank;


import lombok.*;

@Getter
@Setter
@ToString
public class BoardFormDto {
	
	private Long id;
	
	private String writerId;
	
	private String writer;

	@NotBlank(message = "제목을 입력해 주세요.")
	private String title;
	
	
	private String content;
	
	private int commentCount;  
	
	private LocalDateTime RegTime;
	
	private LocalDateTime updateTime;
	
}
