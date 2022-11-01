package com.bom365.custom.payment.KakopayDto;





public class CancelRequestKakaopay {
	
	private String cid;//가맹점 코드, 10자	O
	private String cid_secret;//가맹점 코드 인증키, 24자, 숫자+영문 소문자 조합	X	
	private String tid;//	결제 고유번호	O
	private Integer cancel_amount;//취소 금액	
	private Integer cancel_tax_free_amount;//취소 비과세 금액	O
	private Integer cancel_vat_amount;//취소 부가세 금액
	private Integer cancel_available_amount;//취소 가능 금액(결제 취소 요청 금액 포함)	X
	private String payload;//해당 요청에 대해 저장하고 싶은 값, 최대 200자	X
	
	public CancelRequestKakaopay() {}
	private CancelRequestKakaopay(Builder builder) {
		this.cid = builder.cid;
		this.cid_secret = builder.cid_secret;
		this.tid = builder.tid;
		this.cancel_amount = builder.cancel_amount;
		this.cancel_tax_free_amount = builder.cancel_tax_free_amount;
		this.cancel_vat_amount = builder.cancel_vat_amount;
		this.cancel_available_amount = builder.cancel_available_amount;
		this.payload = builder.payload;
		
	}
	
	public static class Builder {
		private String cid;
		private String cid_secret;
		private String tid;
		private Integer cancel_amount;
		private Integer cancel_tax_free_amount;
		private Integer cancel_vat_amount;
		private Integer cancel_available_amount;
		private String payload;
		
		public Builder cid(String cid) {this.cid = cid;	return this;}
		public Builder cid_secret(String cid_secret) {this.cid_secret = cid_secret;	return this;}
		public Builder tid(String tid) {this.tid = tid;	return this;}
		public Builder cancelAmount(Integer cancel_amount) {this.cancel_amount = cancel_amount;	return this;}
		public Builder cancelTaxFreeAmount(Integer cancel_tax_free_amount) {this.cancel_tax_free_amount = cancel_tax_free_amount;	return this;}
		public Builder cancelVatAmount(Integer cancel_vat_amount) {this.cancel_vat_amount = cancel_vat_amount;	return this;}
		public Builder cancelAvailableAmount(Integer cancel_available_amount) {this.cancel_available_amount = cancel_available_amount;	return this;}
		public Builder payload(String cid) {this.cid = cid;	return this;}
		public CancelRequestKakaopay build() {return new CancelRequestKakaopay(this);}
	}
	
	
	
	
	
	public String getCid() {return cid;}
	public void setCid(String cid) {this.cid = cid;}
	public String getCid_secret() {return cid_secret;}
	public void setCid_secret(String cid_secret) {this.cid_secret = cid_secret;}
	public String getTid() {return tid;}
	public void setTid(String tid) {this.tid = tid;}
	public Integer getCancel_amount() {return cancel_amount;}
	public void setCancel_amount(Integer cancel_amount) {this.cancel_amount = cancel_amount;}
	public Integer getCancel_tax_free_amount() {return cancel_tax_free_amount;}
	public void setCancel_tax_free_amount(Integer cancel_tax_free_amount) {this.cancel_tax_free_amount = cancel_tax_free_amount;}
	public Integer getCancel_vat_amount() {return cancel_vat_amount;}
	public void setCancel_vat_amount(Integer cancel_vat_amount) {this.cancel_vat_amount = cancel_vat_amount;}
	public Integer getCancel_available_amount() {return cancel_available_amount;}
	public void setCancel_available_amount(Integer cancel_available_amount) {this.cancel_available_amount = cancel_available_amount;}
	public String getPayload() {return payload;}
	public void setPayload(String payload) {this.payload = payload;}
	
	
	
}
