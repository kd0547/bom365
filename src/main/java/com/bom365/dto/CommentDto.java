package com.bom365.dto;

import com.bom365.entity.Board;

import lombok.*;


@Setter
@Getter
@ToString
public class CommentDto {
private Board board;
	
	private String writer;
	
	private String Content;
	
}
