package com.bom365.custom.payment.NaverDto;

public class ProductItem {
	private String categoryType;//O	결제 상품 유형. 정의는 별도로 제공되는 결제 상품의 유형, 분류, 식별값을 참고 바랍니다
	private String categoryId;	//O	결제 상품 유형. 정의는 별도로 제공되는 결제 상품의 유형, 분류, 식별값을 참고 바랍니다
	private String uid;			//O	결제 상품 유형. 정의는 별도로 제공되는 결제 상품의 유형, 분류, 식별값을 참고 바랍니다
	private String name;		//O	상품명
	private String payReferrer;	//결제 상품 유형. 정의는 별도로 제공되는 유입경로를 참고 바랍니다
	private String startDate;	//시작일(yyyyMMdd). 예: 20160701결제 상품이 공연, 영화, 보험, 여행, 항공, 숙박인 경우입력을 권장합니다
	private String endDate;		//종료일(yyyyMMdd). 예: 20160701 결제 상품이 공연, 영화, 보험, 여행, 항공, 숙박인 경우 입력을 권장합니다
	private String sellerId;	//가맹점에서 하위 판매자를 식별하기 위해 사용하는 식별키를 전달 합니다. ( 영대소문자 및 숫자만 허용 )
	private int count;			//O	결제 상품 개수. 기본값은 1입니다
	
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPayReferrer() {
		return payReferrer;
	}
	public void setPayReferrer(String payReferrer) {
		this.payReferrer = payReferrer;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
