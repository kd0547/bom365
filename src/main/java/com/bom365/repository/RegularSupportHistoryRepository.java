package com.bom365.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.RegularSupportHistory;

public interface RegularSupportHistoryRepository extends JpaRepository<RegularSupportHistory, Long>{
	
}
