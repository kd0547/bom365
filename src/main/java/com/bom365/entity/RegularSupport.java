package com.bom365.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPay;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="Regular")
public class RegularSupport extends BaseEntity{
	@Id
	@Column(name = "RegularId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String aid;//	요청 고유 번호
	
	private String tid;//	결제 고유 번호
	
	private String cid;	//	가맹점 코드
	
	private String sid;	//	정기결제용 ID, 정기결제 CID로 단건결제 요청 시 발급
	
	private String partner_order_id;// 가맹점 주문번호, 최대 100자

	@Column(name="supporter_id")
	private String supporterId;	// 가맹점 회원 id, 최대 100자
	
	private String payment_method_type; //	결제 수단, CARD 또는 MONEY 중 하나
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "amount_id")
	private Amount amount_id;//결제 금액 정보	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cardInfo_id")
	private CardInfo card_info;//결제 상세 정보, 결제수단이 카드일 경우만 포함
	
	private String item_name;//	상품 이름, 최대 100자
	
	private String item_code;//	상품 코드, 최대 100자
	
	private Integer quantity;//	상품 수량
	
	private LocalDateTime created_at;//	결제 준비 요청 시각
	
	private LocalDateTime approved_at; //	결제 승인 시각
	
	private LocalDateTime nextAt; //다음 결제일 
	
	private String payload; //	결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
	
	public RegularSupport CreateRegularSupportEntity(ApproveResponseKakaoPay approveResponseKakaoPay,LocalDateTime next_at) {
		RegularSupport regularSupport = new RegularSupport();
		
		regularSupport.setAid(approveResponseKakaoPay.getAid());
		regularSupport.setTid(approveResponseKakaoPay.getTid());
		regularSupport.setCid(approveResponseKakaoPay.getCid());
		regularSupport.setSid(approveResponseKakaoPay.getSid());
		regularSupport.setPartner_order_id(approveResponseKakaoPay.getPartner_order_id());
		regularSupport.setSupporterId(approveResponseKakaoPay.getPartner_user_id());
		regularSupport.setPayment_method_type(approveResponseKakaoPay.getPayment_method_type());
		regularSupport.setItem_name(approveResponseKakaoPay.getItem_name());
		regularSupport.setItem_code(approveResponseKakaoPay.getItem_code());
		regularSupport.setQuantity(approveResponseKakaoPay.getQuantity());
		regularSupport.setCreated_at(approveResponseKakaoPay.getCreated_at());
		regularSupport.setApproved_at(approveResponseKakaoPay.getApproved_at());
		regularSupport.setPayload(approveResponseKakaoPay.getPayload());
		regularSupport.setNext_at(next_at);
		
		return regularSupport;
		
	}
	

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public String getAid() {return aid;}
	public void setAid(String aid) {this.aid = aid;}
	public String getTid() {return tid;}
	public void setTid(String tid) {this.tid = tid;}
	public String getCid() {return cid;}
	public void setCid(String cid) {this.cid = cid;}
	public String getSid() {return sid;}
	public void setSid(String sid) {this.sid = sid;}
	public String getPartner_order_id() {return partner_order_id;}
	public void setPartner_order_id(String partner_order_id) {this.partner_order_id = partner_order_id;}
	public String getSupporterId() {return supporterId;}
	public void setSupporterId(String supporterId) {this.supporterId = supporterId;}
	public String getPayment_method_type() {return payment_method_type;}
	public void setPayment_method_type(String payment_method_type) {this.payment_method_type = payment_method_type;}
	public Amount getAmount_id() {return amount_id;}
	public void setAmount_id(Amount amount_id) {this.amount_id = amount_id;}
	public CardInfo getCard_info() {return card_info;}
	public void setCard_info(CardInfo card_info) {this.card_info = card_info;}
	public String getItem_name() {return item_name;}
	public void setItem_name(String item_name) {this.item_name = item_name;}
	public String getItem_code() {return item_code;}
	public void setItem_code(String item_code) {this.item_code = item_code;}
	public Integer getQuantity() {return quantity;}
	public void setQuantity(Integer quantity) {this.quantity = quantity;}
	public LocalDateTime getCreated_at() {return created_at;}
	public void setCreated_at(LocalDateTime created_at) {this.created_at = created_at;}
	public LocalDateTime getApproved_at() {return approved_at;}
	public void setApproved_at(LocalDateTime approved_at) {this.approved_at = approved_at;}
	public String getPayload() {return payload;}
	public void setPayload(String payload) {this.payload = payload;}
	public LocalDateTime getNext_at() {return  nextAt;}
	public void setNext_at(LocalDateTime next_at) {this.nextAt = next_at;}


	@Override
	public String toString() {
		return "RegularSupport [id=" + id + ", aid=" + aid + ", tid=" + tid + ", cid=" + cid + ", sid=" + sid
				+ ", partner_order_id=" + partner_order_id + ", supporterId=" + supporterId + ", payment_method_type="
				+ payment_method_type + ", amount_id=" + amount_id + ", card_info=" + card_info + ", item_name="
				+ item_name + ", item_code=" + item_code + ", quantity=" + quantity + ", created_at=" + created_at
				+ ", approved_at=" + approved_at + ", nextAt=" + nextAt + ", payload=" + payload + "]";
	}
	
	
	
	
	
	
	
	
	
}
