package com.bom365.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bom365.payment.dto.ReadyResponseKakaoPayDto;
import com.bom365.service.KakaoPayService;


import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/temporary")
@RequiredArgsConstructor
public class TemporarySupportController {
	
	private final KakaoPayService kakaoPayService;
	
	
	@GetMapping(value="/payment")
	public String temporaryMain() {
		
		return "support/temporarySupport";
	}
	
	
	@PostMapping(value="/kakaopay")
	public @ResponseBody ReadyResponseKakaoPayDto  temporaryKakaoPay(@RequestParam(name="amount")int amount,Principal principal ,Model model) {
		
		ReadyResponseKakaoPayDto readyResponseKakaoPayDto =  kakaoPayService.payReady(amount, "test");		
		
		//System.out.println("test"+readyResponseKakaoPayDto.toString());
		
		return null;
	}
	
	
}
