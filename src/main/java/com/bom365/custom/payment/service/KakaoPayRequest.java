package com.bom365.custom.payment.service;



import java.net.URI;


import java.net.URISyntaxException;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;

import com.bom365.custom.payment.KakopayDto.ApproveRequestKakaoPay;
import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPay;
import com.bom365.custom.payment.KakopayDto.CancelRequestKakaopay;
import com.bom365.custom.payment.KakopayDto.CancelResponseKakaopayDto;
import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopay;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPay;
import com.fasterxml.jackson.databind.ObjectMapper;


/*

*/


@Service
public class KakaoPayRequest implements paymentRequest{
	//https://intrepidgeeks.com/tutorial/bind-kakapay-api-in-spring-boot
	
	
	@Autowired
	private PaymentSender paymentSender;
	
	

	/*
	 * 카카오페이 결제 요청
	 */
	/*
	public ReadyResponseKakaoPayDto payReady(URI uri,ReadyRequestKakaopayDto readyRequestKakaopayDto)throws RestClientException {
		MultiValueMap<String, String> params = delectNullParam(readyRequestKakaopayDto);
		
		
		return (ReadyResponseKakaoPayDto) paymentSender.send(uri, params, ReadyResponseKakaoPayDto.class);
	}
	*/
	public	Object payReady(URI url,Object formObject,Object toObject) throws RestClientException{
		MultiValueMap<String, String> params = delectNullParam(formObject);
	
		System.out.println("params : " + params.toString());
		
		return  paymentSender.send(url, params, toObject.getClass());
	}
	
	/*
	 * 카카오페이 결제 허용
	 */
	public Object payApprove(URI uri,Object formObject,Object toObject) throws RestClientException{
		MultiValueMap<String, String> params = delectNullParam(formObject);
		
		return paymentSender.send(uri, params, toObject.getClass());
	}
	
	/*
	 * 카카오 페이 결제 취소
	 */
	public CancelResponseKakaopayDto payCancel(URI uri,CancelRequestKakaopay cancelRequestKakaopayDto) throws RestClientException{
		MultiValueMap<String, String> params = delectNullParam(cancelRequestKakaopayDto);

		return (CancelResponseKakaopayDto) paymentSender.send(uri, params, CancelResponseKakaopayDto.class);
	}
	/*
	private Object send(URI uri,MultiValueMap<String, String> params ,Object object) {
		return kakaoPayment.send(uri, params, object.getClass());
	}
	*/
	
	private MultiValueMap<String, String> delectNullParam(Object object) {
		return paymentSender.convert(new ObjectMapper(), object);
	}
	
}
