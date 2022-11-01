package com.bom365.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPay;
import com.bom365.custom.payment.KakopayDto.ReadyResponseSubscription;
import com.bom365.entity.RegularSupport;
import com.bom365.entity.RegularSupportHistory;
import com.bom365.repository.RegularSupportHistoryRepository;

@Service
public class RegularSupportHistoryService {
	@Autowired
	private RegularSupportHistoryRepository regularSupportHistoryRepository;
	
	public RegularSupportHistory save(ApproveResponseKakaoPay approveResponseKakaoPay) {
		RegularSupportHistory regularSupportHistory = RegularSupportHistory.createRegularHistoryEntity(approveResponseKakaoPay);

		
		return regularSupportHistoryRepository.save(regularSupportHistory);
	}
	
	public RegularSupportHistory save(ReadyResponseSubscription readyResponseSubscription) {
		RegularSupportHistory regularSupportHistory = RegularSupportHistory.createRegularHistoryEntity(readyResponseSubscription);

		
		return regularSupportHistoryRepository.save(regularSupportHistory);
	}
}
