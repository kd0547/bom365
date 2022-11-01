package com.bom365.custom.payment.KakopayDto;

import lombok.*;



public class ApproveRequestKakaoPay {
	
	
	private String cid;//가맹점 코드, 10자
	private String cid_secret;//가맹점 코드 인증키, 24자, 숫자+영문 소문자 조합
	private String tid;//결제 고유번호, 결제 준비 API 응답에 포함
	private String partner_order_id;//가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
	private String partner_user_id;//	가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
	private String pg_token;//결제승인 요청을 인증하는 토큰 사용자 결제 수단 선택 완료 시, approval_url로 redirection해줄 때 pg_token을 query string으로 전달
	private String payload;//결제 승인 요청에 대해 저장하고 싶은 값, 최대 200자
	private String total_amount;//상품 총액, 결제 준비 API 요청과 일치해야 함
	
	
	public static class Builder {
		private String cid;
		private String cid_secret;
		private String tid;
		private String partner_order_id;
		private String partner_user_id;
		private String pg_token;
		private String payload;
		private String total_amount;
		
		public Builder cid(String cid) {this.cid = cid;	return this;}
		public Builder cidSecret(String cid_secret) {this.cid_secret = cid_secret;	return this;}
		public Builder tid(String tid) {this.tid = tid;	return this;}
		public Builder partnerOrderId(String partner_order_id) {this.partner_order_id = partner_order_id;	return this;}
		public Builder partnerUserId(String partner_user_id) {this.partner_user_id = partner_user_id;	return this;}
		public Builder pgToken(String pg_token) {this.pg_token = pg_token;	return this;}
		public Builder payload(String payload) {this.payload = payload;	return this;}
		public Builder totalAmount(String total_amount) {this.total_amount = total_amount;	return this;}
		public ApproveRequestKakaoPay build() {return new ApproveRequestKakaoPay(this);}
	}
	public ApproveRequestKakaoPay() {}
	private ApproveRequestKakaoPay(Builder builder) {
		this.cid = builder.cid;
		this.cid_secret = builder.cid_secret;
		this.tid = builder.tid;
		this.partner_order_id = builder.partner_order_id;
		this.partner_user_id = builder.partner_user_id;
		this.pg_token = builder.pg_token;
		this.payload = builder.payload;
		this.total_amount = builder.total_amount;
	}
	
	
	

	public String getCid() {return cid;}
	public void setCid(String cid) {this.cid = cid;}

	public String getCid_secret() {return cid_secret;}
	public void setCid_secret(String cid_secret) {this.cid_secret = cid_secret;}

	public String getTid() {return tid;}
	public void setTid(String tid) {this.tid = tid;}

	public String getPartner_order_id() {return partner_order_id;}
	public void setPartner_order_id(String partner_order_id) {this.partner_order_id = partner_order_id;}

	public String getPartner_user_id() {return partner_user_id;}
	public void setPartner_user_id(String partner_user_id) {this.partner_user_id = partner_user_id;}

	public String getPg_token() {return pg_token;}
	public void setPg_token(String pg_token) {this.pg_token = pg_token;}

	public String getPayload() {return payload;}
	public void setPayload(String payload) {this.payload = payload;}

	public String getTotal_amount() {return total_amount;}
	public void setTotal_amount(String total_amount) {this.total_amount = total_amount;}
	
	@Override
	public String toString() {
		return "ApproveRequestKakaoPay [cid=" + cid + ", cid_secret=" + cid_secret + ", tid=" + tid
				+ ", partner_order_id=" + partner_order_id + ", partner_user_id=" + partner_user_id + ", pg_token="
				+ pg_token + ", payload=" + payload + ", total_amount=" + total_amount + "]";
	}
	
	
	
	
	
}
