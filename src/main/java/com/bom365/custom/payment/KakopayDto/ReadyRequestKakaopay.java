package com.bom365.custom.payment.KakopayDto;



public class ReadyRequestKakaopay {
	
		private String cid; //가맹점 코드, 10자	O	
		private String cid_secret;//	가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합	X
		private String partner_order_id;//	가맹점 주문번호, 최대 100자	O	
		private String partner_user_id;//가맹점 회원 id, 최대 100자	O	
		private String item_name;//상품명, 최대 100자	O	
		private String item_code;//상품코드, 최대 100자	X	
		private Integer quantity;//상품 수량	O		
		private Integer total_amount;//상품 총액	O		
		private Integer tax_free_amount;//상품 비과세 금액	O
		
		//	상품 부가세 금액
		//	값을 보내지 않을 경우 다음과 같이 VAT 자동 계산
		//	(상품총액 - 상품 비과세 금액)/11 : 소숫점 이하 반올림	X
		private Integer vat_amount;	
		private Integer green_deposit;//컵 보증금	X
		private String approval_url;//결제 성공 시 redirect url, 최대 255자	O	
		private String cancel_url;	//	결제 취소 시 redirect url, 최대 255자	O		
		private String fail_url	;	//	결제 실패 시 redirect url, 최대 255자	O
		
		//사용 허가할 카드사 코드*의 배열
		//ex) ["HANA", "BC"]
		//(기본값: 모든 카드사 허용)	X
		//결제 수단으로써 사용 허가할 카드사를 지정해야 하는 경우 사용, 카카오페이와 사전 협의 필요
		//JSON Array available_cards		
		
		//CARD 또는 MONEY 중 하나	X
		//	사용 허가할 결제 수단, 지정하지 않으면 모든 결제 수단 허용
		private String payment_method_type;
		private Integer install_month;//	카드 할부개월, 0~12	X
		
		/*
		{String:String}	결제 화면에 보여줄 사용자 정의 문구, 카카오페이와 사전 협의 필요
		ex) iOS에서 사용자 인증 완료 후 가맹점 앱으로 자동 전환하는 방법(iOS만 예외 처리, 안드로이드 동작 안 함)
		- 다음과 같이 return_custom_url key 정보에 앱스킴을 넣어서 전송
		return_custom_url":"kakaotalk://	X
		*/
		//JSON Map  custom_json	
		private ReadyRequestKakaopay(Builder builder) {
			this.cid = builder.cid;
			this.cid_secret = builder.cidSecret;
			this.partner_order_id= builder.partnerOrderId;
			this.partner_user_id = builder.partnerUserId;
			this.item_name = builder.itemName;
			this.item_code = builder.itemCode;
			this.quantity = builder.quantity;
			this.total_amount = builder.totalAmount;
			this.tax_free_amount = builder.taxFreeAmount;
			this.vat_amount = builder.vatAmount;
			this.green_deposit = builder.greenDeposit;
			this.approval_url = builder.approvalUrl;
			this.cancel_url = builder.cancelUrl;
			this.fail_url = builder.failUrl;
			this.payment_method_type = builder.paymentMethodType;
			this.install_month = builder.installMonth;
		}
		
		
		
		public static class Builder {
			private String cid;	
			private String cidSecret;
			private String partnerOrderId;
			private String partnerUserId;
			private String itemName;
			private String itemCode;
			private Integer quantity;
			private Integer totalAmount;
			private Integer taxFreeAmount;
			private Integer vatAmount;
			private Integer greenDeposit;
			private String approvalUrl;
			private String cancelUrl;
			private String failUrl	;
			private String paymentMethodType;
			private Integer installMonth;
			
			public Builder cid(String cid) {this.cid = cid;	return this;}
			public Builder cidSecret(String cidSecret) {this.cidSecret = cidSecret;	return this;}
			public Builder partnerOrderId(String partnerOrderId) {this.partnerOrderId = partnerOrderId;	return this;}
			public Builder partnerUserId(String partnerUserId) {this.partnerUserId = partnerUserId;	return this;}
			public Builder itemName(String itemName) {this.itemName = itemName;	return this;}
			public Builder itemCode(String itemCode) {this.itemCode = itemCode;	return this;}
			public Builder quantity(Integer quantity) {this.quantity = quantity;	return this;}
			public Builder totalAmount(Integer totalAmount) {this.totalAmount = totalAmount;	return this;}
			public Builder taxFreeAmount(Integer taxFreeAmount) {this.taxFreeAmount = taxFreeAmount;	return this;}
			public Builder vatAmount(Integer vatAmount) {this.vatAmount = vatAmount;	return this;}
			public Builder greenDeposit(Integer greenDeposit) {this.greenDeposit = greenDeposit;	return this;}
			public Builder approvalUrl(String approvalUrl) {this.approvalUrl = approvalUrl;	return this;}
			public Builder cancelUrl(String cancelUrl) {this.cancelUrl = cancelUrl;	return this;}
			public Builder failUrl(String failUrl) {this.failUrl = failUrl;	return this;}
			public Builder paymentMethodType(String paymentMethodType) {this.paymentMethodType = paymentMethodType;	return this;}
			public Builder installMonth(Integer installMonth) {this.installMonth = installMonth;	return this;}
			public ReadyRequestKakaopay build() {return new ReadyRequestKakaopay(this);}
		}



		
		
		
		
		public String getCid() {return cid;}
		public void setCid(String cid) {this.cid = cid;}
		public String getCid_secret() {
			return cid_secret;
		}

		public void setCid_secret(String cid_secret) {
			this.cid_secret = cid_secret;
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
		public String getApproval_url() {
			return approval_url;
		}
		public void setApproval_url(String approval_url) {
			this.approval_url = approval_url;
		}
		public String getCancel_url() {
			return cancel_url;
		}
		public void setCancel_url(String cancel_url) {
			this.cancel_url = cancel_url;
		}

		public String getFail_url() {
			return fail_url;
		}
		public void setFail_url(String fail_url) {
			this.fail_url = fail_url;
		}
		public String getPayment_method_type() {
			return payment_method_type;
		}
		public void setPayment_method_type(String payment_method_type) {
			this.payment_method_type = payment_method_type;
		}

		public Integer getInstall_month() {
			return install_month;
		}
		public void setInstall_month(Integer install_month) {
			this.install_month = install_month;
		}

		@Override
		public String toString() {
			return "ReadyRequestKakaopay [cid=" + cid + ", cid_secret=" + cid_secret + ", partner_order_id="
					+ partner_order_id + ", partner_user_id=" + partner_user_id + ", item_name=" + item_name
					+ ", item_code=" + item_code + ", quantity=" + quantity + ", total_amount=" + total_amount
					+ ", tax_free_amount=" + tax_free_amount + ", vat_amount=" + vat_amount + ", green_deposit="
					+ green_deposit + ", approval_url=" + approval_url + ", cancel_url=" + cancel_url + ", fail_url="
					+ fail_url + ", payment_method_type=" + payment_method_type + ", install_month=" + install_month
					+ "]";
		}
		
		
		
		
		
		
		
		
}
