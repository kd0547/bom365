package com.bom365.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
public class BoardFormDto {
	
	private Long id;
	
	private String writer;

	@NotBlank(message = "제목을 입력해 주세요.")
	private String title;
	
	private int commentCount;  
	
	private LocalDateTime RegTime;
	
	
}
