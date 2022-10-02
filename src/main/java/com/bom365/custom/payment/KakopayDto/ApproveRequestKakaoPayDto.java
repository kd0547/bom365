package com.bom365.custom.payment.KakopayDto;

import lombok.*;


@Setter
@Getter
@ToString
public class ApproveRequestKakaoPayDto {
	
	//가맹점 코드, 10자
	private String cid;
	
	//가맹점 코드 인증키, 24자, 숫자+영문 소문자 조합
	private String cidSecret;
	
	//결제 고유번호, 결제 준비 API 응답에 포함
	private String tid;
	
	//가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
	private String partner_order_id;
	
	//	가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
	private String partner_user_id;
	
	//결제승인 요청을 인증하는 토큰 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달
	private String pg_token;
	
	//결제 승인 요청에 대해 저장하고 싶은 값, 최대 200자
	private String payload;
	
	//상품 총액, 결제 준비 API 요청과 일치해야 함
	private String total_amount;
	
	
	
}
