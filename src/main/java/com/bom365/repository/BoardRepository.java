package com.bom365.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bom365.entity.Board;




public interface BoardRepository extends JpaRepository<Board, Long> {
	
	/*
	 * 	 pageable 객체 없이 페이징 처리 연습 
	 */
	public Long countFindAllBy();
	
	public List<Board> findByIdBetween(@Param("start") Long start,@Param("end") Long end);
	
}
