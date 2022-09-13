package com.bom365.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.entity.TemporarySupport;
import com.bom365.repository.TemporarySupportRepository;

@Service
@Transactional
public class TemporarySupportService {
	
	
	//Autowired를 사용한 의존성 주입
	@Autowired
	private TemporarySupportRepository temporarySupportRepository;
	
	public TemporarySupport save(TemporarySupport temporarySupport) {

		return temporarySupportRepository.save(temporarySupport);
	}
	
	
}
