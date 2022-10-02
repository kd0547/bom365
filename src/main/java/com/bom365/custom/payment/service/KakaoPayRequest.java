package com.bom365.custom.payment.service;



import java.net.URI;


import java.net.URISyntaxException;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.bom365.custom.payment.KakopayDto.ApproveRequestKakaoPayDto;
import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPayDto;
import com.bom365.custom.payment.KakopayDto.CancelRequestKakaopayDto;
import com.bom365.custom.payment.KakopayDto.CancelResponseKakaopayDto;
import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopayDto;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPayDto;
import com.fasterxml.jackson.databind.ObjectMapper;


/*

*/


@Service
public class KakaoPayRequest implements paymentRequest{
	//https://intrepidgeeks.com/tutorial/bind-kakapay-api-in-spring-boot
	
	
	  
	private PaymentSender paymentSender;
	
	public KakaoPayRequest(PaymentSender paymentSender) {
		this.paymentSender = paymentSender;
	}
	
	

	/*
	 * 카카오페이 결제 요청
	 */
	/*
	public ReadyResponseKakaoPayDto payReady(URI uri,ReadyRequestKakaopayDto readyRequestKakaopayDto)throws RestClientException {
		MultiValueMap<String, String> params = delectNullParam(readyRequestKakaopayDto);
		
		
		return (ReadyResponseKakaoPayDto) paymentSender.send(uri, params, ReadyResponseKakaoPayDto.class);
	}
	*/
	public Object payReady(URI url,Object object) {
		MultiValueMap<String, String> params = delectNullParam(object);
		System.out.println("test");
		ReadyResponseKakaoPayDto readyResponseKakaoPayDto;
		
		return  paymentSender.send(url, params, ReadyResponseKakaoPayDto.class);
	}
	
	/*
	 * 카카오페이 결제 허용
	 */
	public ApproveResponseKakaoPayDto payApprove(URI uri,ApproveRequestKakaoPayDto approveRequestKakaoPayDto )throws RestClientException {
		MultiValueMap<String, String> params = delectNullParam(approveRequestKakaoPayDto);
		
		
		return (ApproveResponseKakaoPayDto) paymentSender.send(uri, params, ApproveResponseKakaoPayDto.class);
	}
	
	/*
	 * 카카오 페이 결제 취소
	 */
	public CancelResponseKakaopayDto payCancel(URI uri,CancelRequestKakaopayDto cancelRequestKakaopayDto) throws RestClientException{
		MultiValueMap<String, String> params = delectNullParam(cancelRequestKakaopayDto);

		return (CancelResponseKakaopayDto) paymentSender.send(uri, params, CancelResponseKakaopayDto.class);
	}
	/*
	private Object send(URI uri,MultiValueMap<String, String> params ,Object object) {
		return kakaoPayment.send(uri, params, object.getClass());
	}
	*/
	
	private MultiValueMap<String, String> delectNullParam(Object object) {
		
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		
		return paymentSender.convert(objectMapper, object);
	}
	
}
