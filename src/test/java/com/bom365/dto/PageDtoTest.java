package com.bom365.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.custom.dto.PageDto;
import com.bom365.entity.Board;
import com.bom365.repository.BoardRepository;

@SpringBootTest
class PageDtoTest {

	@Autowired
	BoardRepository boardRepository;
	/*
	@BeforeEach
	void setUp() {
		for(int i=0; i<124; i++) {
			Board board = new Board();
			board.setTitle("title"+i);
			board.setContent("content"+i);
			board.setWriter("writer"+i);
			board.setWriterId("홍길동"+i);
			
			boardRepository.save(board);
		}
	}
	*/
	
	
	@Test
	void test() {
		
		
		
		Long pageSize = 10L;
		
		for(Long i = 1L; i < 124; i++) {
			
			PageDto pageDto = new PageDto();
			pageDto.setTotalPage(boardRepository.countFindAllBy());
			pageDto.setLastPage(pageDto.getTotalPage()/pageDto.getPageSize()+1);
			
			pageDto.setPage(i);
			pageDto.calcStartEndPage();
			
			System.out.println(i+" : "+ pageDto.toString());
		}
		
		
		
		
	}
	
	
	/*
	@Test
	void test() {
		
		
		
		for(int i=1;i<100;i++) {
			PageDto pageDto = new PageDto();
			pageDto.setPage((long) i);
			pageDto.setStartEnd((long) 10);
			//System.out.println(pageDto.toString()); 
			
			List<Board> boardList = boardRepository.findByIdBetween(pageDto.getStart(), pageDto.getEnd());
			
			for(Board board : boardList) {
				System.out.println(board.toString());
			}
			
		}
		
		
		
	}
	*/

}
