package com.bom365.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bom365.custom.pament.dto.ApproveResponseKakaoPayDto;
import com.bom365.custom.pament.dto.ReadyRequestSubscriptionDto;
import com.bom365.dto.KakaoPayFormDto;

@Controller
@RequestMapping(value="/regular")
public class RegularSupportController {
	
	@GetMapping(value="/payment") 
	public String reqularMain(){
		
		
		//return "error/404";
		return "support/regularSupport";
	}
	
	//리팩토링하기
	@PostMapping(value="/kakaopay")
	public void  readyKakaopay(KakaoPayFormDto kakaopayFormDto, Model model) {
		ReadyRequestSubscriptionDto readyRequestSubscriptionDto = new ReadyRequestSubscriptionDto();
		ApproveResponseKakaoPayDto approveResponseKakaoPayDto = null;
		
		
		readyRequestSubscriptionDto.setCid("TCSUBSCRIP");
		readyRequestSubscriptionDto.setItem_name("봄365 후원");
		readyRequestSubscriptionDto.setPartner_order_id("500"+ "12345");
		readyRequestSubscriptionDto.setPartner_user_id(kakaopayFormDto.getId());
		readyRequestSubscriptionDto.setQuantity(1);
		readyRequestSubscriptionDto.setTotal_amount(kakaopayFormDto.getAmount());
		readyRequestSubscriptionDto.setTax_free_amount(0);
		
		
		  
		
		//return readyResponseKakaoPayDto;
	}
	
}
