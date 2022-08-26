package com.bom365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.Member;



public interface MemberRepository extends JpaRepository<Member, Long>{
	Member findBySupporterId(String supporter_id);
	
	
}
