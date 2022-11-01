package com.bom365.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.RegularSupport;
import com.bom365.entity.RegularSupportHistory;

public interface RegularSupportRepository extends JpaRepository<RegularSupport, Long>{
	public RegularSupport findBysupporterId(String supportId);
	
	public List<RegularSupport> findByNextAtBetween(LocalDateTime startTime,LocalDateTime endTime);
}
