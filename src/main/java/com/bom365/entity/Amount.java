package com.bom365.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bom365.dto.AmountDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
public class Amount extends BaseEntity{
	
	@Id
	@Column(name = "amount_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private Integer total;//전체 결제 금액

	private Integer tax_free;//	비과세 금액
	
	private Integer vat; //부가세 금액	
	
	private Integer point;//사용한 포인트 금액
	
	private Integer discount;//할인 금액
	
	private Integer green_deposit;//컵 보증금
	
	public static Amount createAmountEntity(AmountDto amountDto) {
		Amount amount = new Amount();
		amount.setTotal(amountDto.getTotal());
		amount.setTax_free(amountDto.getTax_free());
		amount.setVat(amountDto.getVat());
		amount.setPoint(amountDto.getPoint());
		amount.setDiscount(amountDto.getDiscount());
		amount.setGreen_deposit(amountDto.getGreen_deposit());
		
		return amount;
	}
	
	//getter & setter
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	public Integer getTotal() {return total;}
	public void setTotal(Integer total) {this.total = total;}
	public Integer getTax_free() {return tax_free;}
	public void setTax_free(Integer tax_free) {this.tax_free = tax_free;}
	public Integer getVat() {return vat;}
	public void setVat(Integer vat) {this.vat = vat;}
	public Integer getPoint() {return point;}
	public void setPoint(Integer point) {this.point = point;}
	public Integer getDiscount() {return discount;}
	public void setDiscount(Integer discount) {this.discount = discount;}
	public Integer getGreen_deposit() {return green_deposit;}
	public void setGreen_deposit(Integer green_deposit) {this.green_deposit = green_deposit;}
	
	@Override
	public String toString() {
		return "Amount [id=" + id + ", total=" + total + ", tax_free=" + tax_free + ", vat=" + vat + ", point=" + point
				+ ", discount=" + discount + ", green_deposit=" + green_deposit + "]";
	}	
	
	
	
}
