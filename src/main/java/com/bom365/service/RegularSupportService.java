package com.bom365.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPay;
import com.bom365.entity.Amount;
import com.bom365.entity.CardInfo;
import com.bom365.entity.RegularSupport;
import com.bom365.repository.AmountRepository;
import com.bom365.repository.CardInfoRepository;
import com.bom365.repository.RegularSupportRepository;


@Service
@Transactional
public class RegularSupportService {
	
	@Autowired
	private RegularSupportRepository reqularSupportRepository;
	
	@Autowired
	private CardInfoRepository cardInfoRepository;
	
	@Autowired
	private AmountRepository amountRepository;
	
	
	public RegularSupport find(String supportId) {
		return reqularSupportRepository.findBysupporterId(supportId);
	}
	
	public List<RegularSupport> findByNextAtBetween(LocalDateTime startTime,LocalDateTime endTime) {
		return reqularSupportRepository.findByNextAtBetween(startTime,endTime);
	}
	
	public RegularSupport saveAll(ApproveResponseKakaoPay approveResponseKakaoPay) {

		
		
		RegularSupport regularSupport = new RegularSupport().CreateRegularSupportEntity(approveResponseKakaoPay,approveResponseKakaoPay.getApproved_at().plusMonths(1));
		
		if(approveResponseKakaoPay.getCard_info() != null) {
			CardInfo cardInfo = CardInfo.createCardInfoEntity(approveResponseKakaoPay.getCard_info());
			CardInfo insertCardInfo= cardInfoRepository.save(cardInfo);
			regularSupport.setCard_info(insertCardInfo);
		}
		
		
		Amount amount = Amount.createAmountEntity(approveResponseKakaoPay.getAmount());
		Amount insertAmount = amountRepository.save(amount);	
		regularSupport.setAmount_id(insertAmount);
		
		
		return reqularSupportRepository.save(regularSupport);
		
	}
	
}
