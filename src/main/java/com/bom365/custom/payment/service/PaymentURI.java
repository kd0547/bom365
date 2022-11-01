package com.bom365.custom.payment.service;

import java.net.URI;
import java.net.URISyntaxException;

public class PaymentURI {
	private static final String host= "https://kapi.kakao.com";
	
	//단건결제
	private static final String ready = "/v1/payment/ready";
	private static final String approve = "/v1/payment/approve";
	
	//정기결제 
	private static final String subscribe = "/v1/payment/subscription ";
	
	//정기결제 취소
	private static final String subscribe_inactive = "/v1/payment/manage/subscription/inactive";
	
	//주문 정보
	private static final String subscribe_status =  "/v1/payment/manage/subscription/status";
	
	//주문취소
	private static final String cancel = "/v1/payment/cancel";;
	
	//단건결제
	public static String PaymentReady() {
		return host+ready;
	}
	//단건결제
		public static String PaymentApprove() {
			return host+approve;
		}
	
	//정기결제
	public static String PaymentSubscribe() {
		return host+subscribe;
	}
	
	//정기결제 취소
	public static String PaymentSubInactive() {
		return host+subscribe_inactive;
	}
	
	//정기결제 취소
	public static String PaymentSubStatus() {
		return host+subscribe_status;
	}
	
	//정기결제 취소
	public static String PaymentCancel() {
		return host+cancel;
	}
}
