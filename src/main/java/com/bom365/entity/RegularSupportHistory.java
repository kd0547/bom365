package com.bom365.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.catalina.Host;

import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPay;
import com.bom365.custom.payment.KakopayDto.ReadyResponseSubscription;

@Entity
@Table(name="RegularHistory")
public class RegularSupportHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long historyId;
	
	private String aid;//요청 고유 번호
	
	private String tid;//결제 고유 번호
	
	private String partner_order_id;// 가맹점 주문번호, 최대 100자

	@Column(name="supporter_id")
	private String supporterId;	// 가맹점 회원 id, 최대 100자
	
	private Integer TotalAmount;
	
	private String item_name;//상품 이름, 최대 100자
	
	private String item_code;//상품 코드, 최대 100자
	
	private Integer quantity;//상품 수량
	
	private LocalDateTime approved_at; //결제 승인 시각
	
	
	public static RegularSupportHistory createRegularHistoryEntity(ReadyResponseSubscription readyResponseSubscription) {
		RegularSupportHistory history = new RegularSupportHistory();
		
		history.setAid(readyResponseSubscription.getAid());
		history.setTid(readyResponseSubscription.getTid());
		history.setPartner_order_id(readyResponseSubscription.getPartner_order_id());
		history.setSupporterId(readyResponseSubscription.getPartner_user_id());
		history.setTotalAmount(readyResponseSubscription.getAmount().getTotal());
		history.setItem_name(readyResponseSubscription.getItem_name());
		history.setItem_code(readyResponseSubscription.getItem_code());
		history.setQuantity(readyResponseSubscription.getQuantity());
		history.setApproved_at(readyResponseSubscription.getApproved_at());
		
		return history;
	}
	
	public static RegularSupportHistory createRegularHistoryEntity(ApproveResponseKakaoPay approveResponseKakaoPay) {
		RegularSupportHistory history = new RegularSupportHistory();
		
		history.setAid(approveResponseKakaoPay.getAid());
		history.setTid(approveResponseKakaoPay.getTid());
		history.setPartner_order_id(approveResponseKakaoPay.getPartner_order_id());
		history.setSupporterId(approveResponseKakaoPay.getPartner_user_id());
		history.setTotalAmount(approveResponseKakaoPay.getAmount().getTotal());
		history.setItem_name(approveResponseKakaoPay.getItem_name());
		history.setItem_code(approveResponseKakaoPay.getItem_code());
		history.setQuantity(approveResponseKakaoPay.getQuantity());
		history.setApproved_at(approveResponseKakaoPay.getApproved_at());
		
		return history;
	}
	
	
	public Long getHistoryId() {return historyId;}
	public void setHistoryId(Long historyId) {this.historyId = historyId;}
	public String getAid() {return aid;}
	public void setAid(String aid) {this.aid = aid;}
	public String getTid() {return tid;}
	public void setTid(String tid) {this.tid = tid;}
	public String getPartner_order_id() {return partner_order_id;}
	public void setPartner_order_id(String partner_order_id) {this.partner_order_id = partner_order_id;}
	public String getSupporterId() {return supporterId;}
	public void setSupporterId(String supporterId) {this.supporterId = supporterId;}
	public Integer getTotalAmount() {return TotalAmount;}
	public void setTotalAmount(Integer totalAmount) {TotalAmount = totalAmount;}
	public String getItem_name() {return item_name;}
	public void setItem_name(String item_name) {this.item_name = item_name;}
	public String getItem_code() {return item_code;}
	public void setItem_code(String item_code) {this.item_code = item_code;}
	public Integer getQuantity() {return quantity;}
	public void setQuantity(Integer quantity) {this.quantity = quantity;}
	public LocalDateTime getApproved_at() {return approved_at;}
	public void setApproved_at(LocalDateTime approved_at) {this.approved_at = approved_at;}
	
	
	
}
