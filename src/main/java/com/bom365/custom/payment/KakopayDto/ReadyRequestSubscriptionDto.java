package com.bom365.custom.payment.KakopayDto;

public class ReadyRequestSubscriptionDto {
	private String cid		;//가맹점 코드, 10자	O
	private String cid_secret		;//가맹점 코드 인증 키(Key), 24자 숫자+영문 소문자 조합	X
	private String sid		;//정기결제 키, 20자	O
	private String partner_order_id		;//가맹점 주문번호, 최대 100자	O
	private String partner_user_id		;//가맹점 회원 id, 최대 100자 SID를 발급 받은 첫 결제의 결제 준비 API로 전달한 값과 일치해야 함	O
	private String item_name		;//상품명, 최대 100자	X
	private String item_code		;//상품코드, 최대 100자	X
	private Integer quantity		;//상품 수량	O
	private Integer total_amount		;//상품 총액	O
	private Integer tax_free_amount	;//	상품 비과세 금액	O
	private Integer vat_amount		;//상품 부가세 금액 기본 값: (상품총액 - 상품 비과세 금액)/11, 소수점 이하 반올림	X
	private Integer green_deposit		;//컵 보증금	X
	private String payload		;//결제 승인 요청에 대해 저장하고 싶은 값, 최대 200자	X
	
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
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public String getPartner_user_id() {
		return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	public Integer getTax_free_amount() {
		return tax_free_amount;
	}
	public void setTax_free_amount(Integer tax_free_amount) {
		this.tax_free_amount = tax_free_amount;
	}
	public Integer getVat_amount() {
		return vat_amount;
	}
	public void setVat_amount(Integer vat_amount) {
		this.vat_amount = vat_amount;
	}
	public Integer getGreen_deposit() {
		return green_deposit;
	}
	public void setGreen_deposit(Integer green_deposit) {
		this.green_deposit = green_deposit;
	}
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
}
