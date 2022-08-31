package com.bom365.dto;



import org.springframework.http.HttpStatus;
import com.bom365.constant.Duplicate;

import lombok.*;


@Getter
@Setter
public class DuplicateDto {
	
	
	
	private HttpStatus httpStatus;
	
	private int status;
	
	private String message;
	
	private Duplicate duplicate;
	
	public DuplicateDto() {
		
	}
	
	public DuplicateDto(HttpStatus httpStatus,int status,String message,Duplicate duplicate) {
		this.httpStatus = httpStatus;
		this.status = status;
		this.message = message;
		this.duplicate = duplicate;
	}
	
	
}
