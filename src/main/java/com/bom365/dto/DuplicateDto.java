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
	
	
	public static class Builder {
		private HttpStatus httpStatus;
		private int status;
		private String message;
		private Duplicate duplicate;
		
		public Builder httpStatus(HttpStatus httpStatus) {this.httpStatus = httpStatus;return this;}
		public Builder status(int status) {this.status = status; return this;}
		public Builder message(String message) {this.message = message; return this;}
		public Builder duplicate(Duplicate duplicate) {this.duplicate = duplicate; return this;}
		public DuplicateDto build() {return new DuplicateDto(this);}
	}
	
	
	public DuplicateDto() {}
	public DuplicateDto(HttpStatus httpStatus,int status,String message,Duplicate duplicate) {
		this.httpStatus = httpStatus;
		this.status = status;
		this.message = message;
		this.duplicate = duplicate;
	}
	private DuplicateDto(Builder builder) {
		this.httpStatus = builder.httpStatus;
		this.status = builder.status;
		this.message = builder.message;
		this.duplicate = builder.duplicate;
	}
	
}
