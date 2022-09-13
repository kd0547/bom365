package com.bom365.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bom365.constant.Amount;
import com.bom365.constant.CardInfo;
import com.bom365.custom.pament.dto.ApproveResponseKakaoPayDto;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;


@Getter
@Setter
@ToString
@Entity
@Table(name="Temporary")
public class TemporarySupport extends BaseEntity{
	
	@Id
	@Column(name = "temporaryId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	//	요청 고유 번호
	private String aid;
	
	//	결제 고유 번호
	private String tid;
	
	//	가맹점 코드
	private String cid;	
	
	//	정기결제용 ID, 정기결제 CID로 단건결제 요청 시 발급
	private String sid;	
	
	// 가맹점 주문번호, 최대 100자
	
	private String partner_order_id;
	// 가맹점 회원 id, 최대 100자
	@Column(name="supporter_id")
	private String partner_user_id;
	
	//	결제 수단, CARD 또는 MONEY 중 하나
	private String payment_method_type; 
	//	결제 금액 정보
	//private Amount amount;	
	
	//	결제 상세 정보, 결제수단이 카드일 경우만 포함
	
	//private CardInfo card_info;
	
	//	상품 이름, 최대 100자
	private String item_name;
	
	//	상품 코드, 최대 100자
	private String item_code;
	
	//	상품 수량
	private Integer quantity;
	
	//	결제 준비 요청 시각
	private LocalDateTime created_at;
	//	결제 승인 시각
	private LocalDateTime approved_at; 
	//	결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용
	private String payload; 
	
	public static TemporarySupport createTemporarySupport(ApproveResponseKakaoPayDto readyResponseKakaoPayDto) {
		TemporarySupport temporarySupport = new TemporarySupport();
		
		temporarySupport.setAid(readyResponseKakaoPayDto.getAid());
		temporarySupport.setTid(readyResponseKakaoPayDto.getTid());
		temporarySupport.setCid(readyResponseKakaoPayDto.getCid());
		temporarySupport.setSid(readyResponseKakaoPayDto.getSid());
		temporarySupport.setPartner_order_id(readyResponseKakaoPayDto.getPartner_order_id());
		temporarySupport.setPartner_user_id(readyResponseKakaoPayDto.getPartner_user_id());
		temporarySupport.setPayment_method_type(readyResponseKakaoPayDto.getPayment_method_type());
		temporarySupport.setItem_name(readyResponseKakaoPayDto.getItem_name());
		temporarySupport.setItem_code(temporarySupport.getItem_code());
		temporarySupport.setQuantity(readyResponseKakaoPayDto.getQuantity());
		temporarySupport.setCreated_at(readyResponseKakaoPayDto.getCreated_at());
		temporarySupport.setApproved_at(readyResponseKakaoPayDto.getApproved_at());
		temporarySupport.setPayload(readyResponseKakaoPayDto.getPayload());
		
		
		return temporarySupport;
	}
	
	
}
