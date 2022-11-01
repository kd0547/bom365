package com.bom365.custom.payment.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.MultiValueMap;

import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopay;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class KakaoPaySenderTest {
	
	@Autowired
	PaymentSender paymentSender;
	
	
	ReadyRequestKakaopay setting() {
		ReadyRequestKakaopay readyRequestKakaopay = 
				new ReadyRequestKakaopay
					.Builder()
						.cid("TC0ONETIME")
						.itemName("봄365 후원")
						.partnerOrderId("500"+ "12345")
						.partnerUserId("test")
						.quantity(1)
						.totalAmount(500)
						.taxFreeAmount(0)	
						.approvalUrl("http://localhost:8080/temporary/kakaopayApproval")
						.cancelUrl("http://localhost:8080/temporary/kakaopayCancel")
						.failUrl("http://localhost:8080/temporary/kakaopayFail")
						.build();

		System.out.println(readyRequestKakaopay.toString());
		
		return readyRequestKakaopay;
	}

	@Test
	void test() {
		MultiValueMap<String, String> convertValue = paymentSender.convert(new ObjectMapper(), setting());
		System.out.println("======================");
		System.out.println(convertValue.toString());
		
	}

}
