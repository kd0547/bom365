package com.bom365.constant;

import lombok.*;


@Getter
@Setter
@ToString
public class Amount {
	// 전체 결제 금액
	private Integer total; 
	//	비과세 금액
	private Integer tax_free;
	//	부가세 금액	
	private Integer vat; 
	//	사용한 포인트 금액
	private Integer point; 
	//	할인 금액
	private Integer discount; 	
	//	컵 보증금
	private Integer green_deposit;	
}
