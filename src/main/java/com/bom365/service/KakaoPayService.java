package com.bom365.service;



import java.net.URI;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bom365.custom.pament.dto.ApproveRequestKakaoPayDto;
import com.bom365.custom.pament.dto.ApproveResponseKakaoPayDto;
import com.bom365.custom.pament.dto.CancelRequestKakaopayDto;
import com.bom365.custom.pament.dto.CancelResponseKakaopayDto;
import com.bom365.custom.pament.dto.ReadyRequestKakaopayDto;
import com.bom365.custom.pament.dto.ReadyResponseKakaoPayDto;
import com.bom365.custom.pament.service.KakaoPayment;
import com.bom365.custom.pament.service.PaymentService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/*

*/


@Service
public class KakaoPayService {
	//https://intrepidgeeks.com/tutorial/bind-kakapay-api-in-spring-boot
	@Autowired
	KakaoPayment kakaoPayment;
	
	
	/*
		리팩토링하기 
	*/
	//카카오페이 결제 요청
	public ReadyResponseKakaoPayDto payReady(ReadyRequestKakaopayDto readyRequestKakaopayDto) {
		ObjectMapper objectMapper = new ObjectMapper();
		MultiValueMap<String, String> params = kakaoPayment.convert(objectMapper, readyRequestKakaopayDto);
		ReadyResponseKakaoPayDto readyResponseKakaoPayDto = null;
		
		
		try {
			readyResponseKakaoPayDto = kakaoPayment.send(new URI(kakaoPayment.PaymentReady()), params, ReadyResponseKakaoPayDto.class);
		}  catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return readyResponseKakaoPayDto;
	}
	
	//카카오페이 결제 허용
	public ApproveResponseKakaoPayDto payApprove(ApproveRequestKakaoPayDto approveRequestKakaoPayDto ) {
		ObjectMapper objectMapper = new ObjectMapper();
		MultiValueMap<String, String> params = kakaoPayment.convert(objectMapper, approveRequestKakaoPayDto);
		ApproveResponseKakaoPayDto readyResponseKakaoPayDto = null;
		
		try {
			readyResponseKakaoPayDto = kakaoPayment.send(new URI(kakaoPayment.PaymentApprove()), params, ApproveResponseKakaoPayDto.class);
		}  catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return readyResponseKakaoPayDto;
	}
	
	//카카오 페이 결제 취소
	public CancelResponseKakaopayDto payCancel(CancelRequestKakaopayDto cancelRequestKakaopayDto) {
		ObjectMapper objectMapper = new ObjectMapper();
		MultiValueMap<String, String> params = kakaoPayment.convert(objectMapper, cancelRequestKakaopayDto);
		CancelResponseKakaopayDto cancelResponseKakaopayDto = null;
		
		try {
			cancelResponseKakaopayDto = kakaoPayment.send(new URI(kakaoPayment.PaymentCancel()), params, CancelResponseKakaopayDto.class);
		}  catch (RestClientException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cancelResponseKakaopayDto;
	}
	
}
