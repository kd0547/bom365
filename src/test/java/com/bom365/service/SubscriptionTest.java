package com.bom365.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import com.bom365.custom.payment.KakopayDto.ReadyRequestSubscription;
import com.bom365.custom.payment.KakopayDto.ReadyResponseSubscription;
import com.bom365.custom.payment.service.KakaoPayRequest;
import com.bom365.custom.payment.service.PaymentURI;
import com.bom365.custom.payment.service.paymentRequest;
import com.bom365.entity.Amount;
import com.bom365.entity.RegularSupport;

@SpringBootTest
public class SubscriptionTest {
	
	@Autowired
	RegularSupportService regularSupportService;
	
	@Autowired
	KakaoPayRequest kakaoPayRequest;
	
	@Test
	@Transactional
	void subscriptionTest() {
		
		LocalDateTime startTime = LocalDateTime.of(2022,11,30,00,00,00);
		LocalDateTime endTime = LocalDateTime.of(2022,11,30,23,59,59);
		
		
		List<RegularSupport> regularSupports = regularSupportService.findByNextAtBetween(startTime, endTime);
		
		
		RegularSupport regularSupport = regularSupports.get(0);
		
		
		ReadyRequestSubscription readyRequestSubscription 
			= new ReadyRequestSubscription
					.Builder()
					.cid(regularSupport.getCid())
					.sid(regularSupport.getSid())
					.partnerOrderId(regularSupport.getPartner_order_id())
					.partnerUserId(regularSupport.getSupporterId())
					.quantity(regularSupport.getQuantity())
					.totalAmount(regularSupport.getAmount_id().getTotal())
					.taxFreeAmount(regularSupport.getAmount_id().getTax_free())
					.build();
		System.out.println(readyRequestSubscription.toString());
		
		try {
			ReadyResponseSubscription readyResponseSubscription = (ReadyResponseSubscription) kakaoPayRequest.payReady(new URI("https://kapi.kakao.com/v1/payment/subscription"), readyRequestSubscription, new ReadyResponseSubscription());
			
			
		
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
