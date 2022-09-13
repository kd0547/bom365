package com.bom365.dto;

import java.time.LocalDateTime;

import lombok.*;


@Getter
@Setter
@ToString
public class ContentDto {
	
	private String boardid;
	
	private String writerId;
	
	private String writer;
	
	private String title;
	
	private String content;
	
	private LocalDateTime RegTime;
}
