package com.bom365.custom.payment.NaverDto;

import lombok.ToString;


public class ReadyResponseNaverpayDto {
	
	
	private String Success;
	private String InvalidMerchant;
	/*
	private body body;
	
	class body {
		private String reserveId;

		public String getReserveId() {
			return reserveId;
		}

		public void setReserveId(String reserveId) {
			this.reserveId = reserveId;
		}
		
	}
	*/
	public String getSuccess() {
		return Success;
	}

	public void setSuccess(String success) {
		Success = success;
	}

	public String getInvalidMerchant() {
		return InvalidMerchant;
	}

	public void setInvalidMerchant(String invalidMerchant) {
		InvalidMerchant = invalidMerchant;
	}
	/*
	public body getBody() {
		return body;
	}

	public void setBody(body body) {
		this.body = body;
	}
	*/	
	@Override
	public String toString() {
		return "ReadyResponseNaverpayDto [Success=" + Success + ", InvalidMerchant=" + InvalidMerchant + "]";
	}
	
	
}
