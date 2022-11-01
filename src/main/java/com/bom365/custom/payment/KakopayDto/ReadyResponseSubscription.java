package com.bom365.custom.payment.KakopayDto;

import java.time.LocalDateTime;

import com.bom365.dto.AmountDto;
import com.bom365.dto.CardInfoDto;

public class ReadyResponseSubscription {
	private String aid;				//Request 고유 번호
	private String tid;				//결제 고유 번호
	private String cid;				//가맹점 코드
	private String sid;				//정기(배치)결제 고유 번호. 20자
	private String partner_order_id;//가맹점 주문번호
	private String partner_user_id;				//가맹점 회원 id
	private String payment_method_type;		//결제 수단, CARD 또는 MONEY 중 하나
	private AmountDto amount;		//결제 금액 정보
	private CardInfoDto card_info;		//결제 상세 정보, 결제수단이 카드일 경우만 포함
	private String item_name;	//상품 이름, 최대 100자
	private String item_code;		//상품 코드, 최대 100자
	private Integer quantity;		//상품 수량
	private LocalDateTime created_at;	//결제 준비 요청 시각
	private LocalDateTime approved_at;	//결제 승인 시각
	private String payload;		//결제 요청 시 전달했던 값
	
	public static class Builder {
		private String aid;	
		private String tid;
		private String cid;
		private String sid;
		private String partner_order_id;
		private String partner_user_id;
		private String payment_method_type;
		private AmountDto amount;
		private CardInfoDto card_info;
		private String item_name;
		private String item_code;
		private Integer quantity;
		private LocalDateTime created_at;
		private LocalDateTime approved_at;
		private String payload;
		
		
		
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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

	public String getPayment_method_type() {
		return payment_method_type;
	}

	public void setPayment_method_type(String payment_method_type) {
		this.payment_method_type = payment_method_type;
	}

	public AmountDto getAmount() {
		return amount;
	}

	public void setAmount(AmountDto amount) {
		this.amount = amount;
	}

	public CardInfoDto getCard_info() {
		return card_info;
	}

	public void setCard_info(CardInfoDto card_info) {
		this.card_info = card_info;
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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getApproved_at() {
		return approved_at;
	}

	public void setApproved_at(LocalDateTime approved_at) {
		this.approved_at = approved_at;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	
}
