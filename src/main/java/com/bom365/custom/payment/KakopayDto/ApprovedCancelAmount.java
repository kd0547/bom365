package com.bom365.custom.payment.KakopayDto;

public class ApprovedCancelAmount {
	private Integer total;		//이번 요청으로 취소된 전체 금액
	private Integer tax_free;		//이번 요청으로 취소된 비과세 금액
	private Integer vat;		//이번 요청으로 취소된 부가세 금액
	private Integer point;		//이번 요청으로 취소된 포인트 금액
	private Integer discount;		//이번 요청으로 취소된 할인 금액
	private Integer green_deposit;		//컵 보증금
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getTax_free() {
		return tax_free;
	}
	public void setTax_free(Integer tax_free) {
		this.tax_free = tax_free;
	}
	public Integer getVat() {
		return vat;
	}
	public void setVat(Integer vat) {
		this.vat = vat;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getGreen_deposit() {
		return green_deposit;
	}
	public void setGreen_deposit(Integer green_deposit) {
		this.green_deposit = green_deposit;
	}
	
	
}
