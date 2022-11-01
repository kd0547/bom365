package com.bom365.scheduler;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import com.bom365.custom.payment.KakopayDto.ReadyRequestSubscription;
import com.bom365.custom.payment.KakopayDto.ReadyResponseSubscription;
import com.bom365.custom.payment.service.KakaoPayRequest;
import com.bom365.entity.RegularSupport;
import com.bom365.repository.RegularSupportRepository;
import com.bom365.service.RegularSupportHistoryService;
import com.bom365.service.RegularSupportService;

@Component
public class RegularSupportScheduler {
	
	@Autowired
	RegularSupportService regularSupportService;
	
	@Autowired
	RegularSupportHistoryService historyService;
	
	@Autowired
	KakaoPayRequest kakaoPayRequest;
	
	
	@Async
	@Scheduled(cron = "0 30 0 * * *")
	@Transactional
	public void subscription() throws InterruptedException {
		
		LocalDateTime startTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
		LocalDateTime endTime = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
		
		List<RegularSupport> regularSupports = regularSupportService.findByNextAtBetween(startTime, endTime);
		
		for(RegularSupport regularSupport : regularSupports) {
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
			
			
			
			try {
				ReadyResponseSubscription readyResponseSubscription = (ReadyResponseSubscription) kakaoPayRequest.payReady(new URI("https://kapi.kakao.com/v1/payment/subscription"), readyRequestSubscription, new ReadyResponseSubscription());
				
				historyService.save(readyResponseSubscription);
				
			
			} catch (RestClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
	}

}
