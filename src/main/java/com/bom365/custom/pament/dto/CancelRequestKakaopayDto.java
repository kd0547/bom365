package com.bom365.custom.pament.dto;





public class CancelRequestKakaopayDto {
	//가맹점 코드, 10자	O
	private String cid;
	
	//가맹점 코드 인증키, 24자, 숫자+영문 소문자 조합	X
	private String cid_secret;	
	
	//	결제 고유번호	O
	private String tid;
	
	//취소 금액	O
	private Integer cancel_amount;
	//취소 비과세 금액	O
	private Integer cancel_tax_free_amount;
	//취소 부가세 금액
	//요청 시 값을 전달하지 않을 경우, (취소 금액 - 취소 비과세 금액)/11, 소숫점이하 반올림	X
	private Integer cancel_vat_amount;
	
	//취소 가능 금액(결제 취소 요청 금액 포함)	X
	private Integer cancel_available_amount;
	//해당 요청에 대해 저장하고 싶은 값, 최대 200자	X
	private String payload;
	
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCid_secret() {
		return cid_secret;
	}
	public void setCid_secret(String cid_secret) {
		this.cid_secret = cid_secret;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public Integer getCancel_amount() {
		return cancel_amount;
	}
	public void setCancel_amount(Integer cancel_amount) {
		this.cancel_amount = cancel_amount;
	}
	public Integer getCancel_tax_free_amount() {
		return cancel_tax_free_amount;
	}
	public void setCancel_tax_free_amount(Integer cancel_tax_free_amount) {
		this.cancel_tax_free_amount = cancel_tax_free_amount;
	}
	public Integer getCancel_vat_amount() {
		return cancel_vat_amount;
	}
	public void setCancel_vat_amount(Integer cancel_vat_amount) {
		this.cancel_vat_amount = cancel_vat_amount;
	}
	public Integer getCancel_available_amount() {
		return cancel_available_amount;
	}
	public void setCancel_available_amount(Integer cancel_available_amount) {
		this.cancel_available_amount = cancel_available_amount;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
	
}
