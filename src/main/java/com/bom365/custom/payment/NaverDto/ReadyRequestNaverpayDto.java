package com.bom365.custom.payment.NaverDto;

import java.util.ArrayList;

public class ReadyRequestNaverpayDto {
	private String modelVersion;	//결제 연동 방식 22년 8월 현재 default model 2 방식으로 고정 동작합니다
	private String merchantPayKey; 	//O	가맹점 주문내역 확인 가능한 가맹점 결제번호 또는 주문번호를 전달해야 합니다
	private String merchantUserKey;	//가맹점의 사용자 키(개인 아이디와 같은 개인정보 데이터는 제외하여 전달해야 합니다)
	private String productName;		//O	대표 상품명. 예: 장미의 이름 외 1건(X), 장미의 이름(O)
	private int productCount;		//O	상품 수량 예: A 상품 2개 + B 상품 1개의 경우 productCount 3으로 전달
	private int totalPayAmount;		//O	총 결제 금액. 최소 결제금액은 100원
	private int deliveryFee;		//배송비
	private int taxScopeAmount;	 	//O	과세 대상 금액. 과세 대상 금액 + 면세 대상 금액 = 총 결제 금액
	private int taxExScopeAmount;	//O	면세 대상 금액. 과세 대상 금액 + 면세 대상 금액 = 총 결제 금액
	private String returnUrl;		//O	결제 완료 후 이동할 URL(returnUrl + 가맹점 파라미터 전달이 가능합니다)
									//네이버페이는 결제 작업 완료 후, 가맹점이 등록한 returnUrl로 리디렉션을 수행합니다
									//가맹점은 이를 활용하여 내부 처리를 수행하거나 구매자에게 결제 결과 화면을 노출할 수 있습니다
	private String purchaserName;	//구매자 성명. 결제 상품이 보험 및 위험 업종 등인 경우에만 필수 값입니다. 그 외에는 전달할 필요가 없습니다
	private String purchaserBirthday;//구매자 생년월일(yyyymmdd). 결제 상품이 보험 및 위험 업종 등인 경우에만 필수 값입니다. 그 외에는 전달할 필요가 없습니다
	private ArrayList<ProductItem>productItems;//productItem 배열. 자세한 내용은 "표 2 productItem"을 참고 바랍니다
	public String getModelVersion() {
		return modelVersion;
	}
	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}
	public String getMerchantPayKey() {
		return merchantPayKey;
	}
	public void setMerchantPayKey(String merchantPayKey) {
		this.merchantPayKey = merchantPayKey;
	}
	public String getMerchantUserKey() {
		return merchantUserKey;
	}
	public void setMerchantUserKey(String merchantUserKey) {
		this.merchantUserKey = merchantUserKey;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getTotalPayAmount() {
		return totalPayAmount;
	}
	public void setTotalPayAmount(int totalPayAmount) {
		this.totalPayAmount = totalPayAmount;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public int getTaxScopeAmount() {
		return taxScopeAmount;
	}
	public void setTaxScopeAmount(int taxScopeAmount) {
		this.taxScopeAmount = taxScopeAmount;
	}
	public int getTaxExScopeAmount() {
		return taxExScopeAmount;
	}
	public void setTaxExScopeAmount(int taxExScopeAmount) {
		this.taxExScopeAmount = taxExScopeAmount;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getPurchaserName() {
		return purchaserName;
	}
	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}
	public String getPurchaserBirthday() {
		return purchaserBirthday;
	}
	public void setPurchaserBirthday(String purchaserBirthday) {
		this.purchaserBirthday = purchaserBirthday;
	}
	public ArrayList<ProductItem> getProductItems() {
		return productItems;
	}
	public void setProductItems(ArrayList<ProductItem> productItems) {
		this.productItems = productItems;
	}
	
	
	
}
