package com.bom365.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bom365.entity.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {

}
