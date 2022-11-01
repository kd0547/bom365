package com.bom365.entity;

import javax.persistence.*;

import com.bom365.dto.CardInfoDto;


@Entity
public class CardInfo extends BaseEntity{
	
		@Id
		@Column(name = "cardInfo_id")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		private String purchase_corp;//매입 카드사 한글명
		
		private String purchase_corp_code;//매입 카드사 코드
		
		private String  issuer_corp;//발급사 카드  한글명
		
		private String issuer_corp_code;//카드 발급사 코드
	
		private String kakaopay_purchase_corp;//카카오페이 매입사명
		
		private String kakaopay_purchase_corp_code;//카카오페이 매입사 코드
		
		private String kakaopay_issuer_corp;//카카오페이 발급사명
		
		private String kakaopay_issuer_corp_code;//	카카오페이 발급사 코드
		
		private String bin;//카드 BIN
		
		private String card_type;//카드 타입
		
		private String install_month;//할부 개월 수
		
		private String approved_id;//카드사 승인번호
		
		private String card_mid;//카드사 가맹점 번호
		
		private String interest_free_install;//무이자할부 여부(Y/N)
		
		private String card_item_code;//카드 상품 코드

		public static CardInfo createCardInfoEntity(CardInfoDto cardInfoDto) {
			CardInfo cardInfo = new CardInfo();
			
			cardInfo.setPurchase_corp(cardInfo.getPurchase_corp());
			cardInfo.setPurchase_corp_code(cardInfo.getPurchase_corp_code());
			cardInfo.setIssuer_corp(cardInfo.getIssuer_corp());
			cardInfo.setIssuer_corp_code(cardInfo.getIssuer_corp_code());
			cardInfo.setKakaopay_purchase_corp(cardInfo.getKakaopay_purchase_corp());
			cardInfo.setKakaopay_purchase_corp_code(cardInfo.getKakaopay_purchase_corp_code());
			cardInfo.setKakaopay_issuer_corp(cardInfo.getKakaopay_issuer_corp());
			cardInfo.setKakaopay_issuer_corp_code(cardInfo.getKakaopay_issuer_corp_code());
			cardInfo.setBin(cardInfo.getBin());
			cardInfo.setCard_type(cardInfo.getCard_type());
			cardInfo.setInstall_month(cardInfo.getInstall_month());
			cardInfo.setApproved_id(cardInfo.getApproved_id());
			cardInfo.setKakaopay_issuer_corp(cardInfo.getKakaopay_issuer_corp());
			cardInfo.setCard_mid(cardInfo.getCard_mid());
			cardInfo.setInterest_free_install(cardInfo.getInterest_free_install());
			cardInfo.setCard_item_code(cardInfo.getCard_item_code());
			
			
			return cardInfo;
		}
		
		//getter & setter
		public Long getId() {return id;}
		public void setId(Long id) {this.id = id;}
		public String getPurchase_corp() {return purchase_corp;}
		public void setPurchase_corp(String purchase_corp) {this.purchase_corp = purchase_corp;}
		public String getPurchase_corp_code() {return purchase_corp_code;}
		public void setPurchase_corp_code(String purchase_corp_code) {this.purchase_corp_code = purchase_corp_code;}
		public String getIssuer_corp() {return issuer_corp;}
		public void setIssuer_corp(String issuer_corp) {this.issuer_corp = issuer_corp;}
		public String getIssuer_corp_code() {return issuer_corp_code;}
		public void setIssuer_corp_code(String issuer_corp_code) {this.issuer_corp_code = issuer_corp_code;}
		public String getKakaopay_purchase_corp() {return kakaopay_purchase_corp;}
		public void setKakaopay_purchase_corp(String kakaopay_purchase_corp) {this.kakaopay_purchase_corp = kakaopay_purchase_corp;}
		public String getKakaopay_purchase_corp_code() {return kakaopay_purchase_corp_code;}
		public void setKakaopay_purchase_corp_code(String kakaopay_purchase_corp_code) {this.kakaopay_purchase_corp_code = kakaopay_purchase_corp_code;}
		public String getKakaopay_issuer_corp() {return kakaopay_issuer_corp;}
		public void setKakaopay_issuer_corp(String kakaopay_issuer_corp) {this.kakaopay_issuer_corp = kakaopay_issuer_corp;}
		public String getKakaopay_issuer_corp_code() {return kakaopay_issuer_corp_code;}
		public void setKakaopay_issuer_corp_code(String kakaopay_issuer_corp_code) {this.kakaopay_issuer_corp_code = kakaopay_issuer_corp_code;}
		public String getBin() {return bin;}
		public void setBin(String bin) {this.bin = bin;}
		public String getCard_type() {return card_type;}
		public void setCard_type(String card_type) {this.card_type = card_type;}
		public String getInstall_month() {return install_month;}
		public void setInstall_month(String install_month) {this.install_month = install_month;}
		public String getApproved_id() {return approved_id;}
		public void setApproved_id(String approved_id) {this.approved_id = approved_id;}
		public String getCard_mid() {return card_mid;}
		public void setCard_mid(String card_mid) {this.card_mid = card_mid;}
		public String getInterest_free_install() {return interest_free_install;}
		public void setInterest_free_install(String interest_free_install) {this.interest_free_install = interest_free_install;}
		public String getCard_item_code() {return card_item_code;}
		public void setCard_item_code(String card_item_code) {this.card_item_code = card_item_code;}
		
		@Override
		public String toString() {
			return "CardInfo [id=" + id + ", purchase_corp=" + purchase_corp + ", purchase_corp_code="
					+ purchase_corp_code + ", issuer_corp=" + issuer_corp + ", issuer_corp_code=" + issuer_corp_code
					+ ", kakaopay_purchase_corp=" + kakaopay_purchase_corp + ", kakaopay_purchase_corp_code="
					+ kakaopay_purchase_corp_code + ", kakaopay_issuer_corp=" + kakaopay_issuer_corp
					+ ", kakaopay_issuer_corp_code=" + kakaopay_issuer_corp_code + ", bin=" + bin + ", card_type="
					+ card_type + ", install_month=" + install_month + ", approved_id=" + approved_id + ", card_mid="
					+ card_mid + ", interest_free_install=" + interest_free_install + ", card_item_code="
					+ card_item_code + "]";
		}
		
		
		
		
}
